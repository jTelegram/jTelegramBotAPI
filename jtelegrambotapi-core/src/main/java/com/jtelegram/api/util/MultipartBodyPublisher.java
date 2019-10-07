package com.jtelegram.api.util;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: replace with an actual proper implementation if Oracle ever gets to it
public class MultipartBodyPublisher implements HttpRequest.BodyPublisher {

    // HEADERS\r\n
    //\r\n
    //--boundary\r\n
    //SUBHEADERS\r\n
    //\r\n
    //stuff\r\n
    //--boundary--

    private static final String CRLF = "\r\n";

    private long contentLength;

    private List<HttpRequest.BodyPublisher> bodyPublishers;

    private Flow.Subscriber<? super ByteBuffer> subscriber;

    private Flow.Subscription currentSubscription;

    private long requested = 0L;

    private boolean currentlySubscribed;

    public MultipartBodyPublisher(String boundaryDelimiter, List<Part> parts) {
        this.contentLength = parts.stream().mapToLong(Part::contentLength).sum()
                + (boundaryDelimiter.length() + CRLF.length()) * (parts.size() + 1);
        String boundary = "--" + boundaryDelimiter + CRLF;
        List<HttpRequest.BodyPublisher> publishers = parts.stream()
                .flatMap(part -> {
                    String headers = CRLF;
                    if (!part.headers.isEmpty()) {
                        headers = String.join(CRLF, part.headers) + CRLF + CRLF;
                    }
                    return Stream.of(
                            BodyPublishers.ofString(boundary, StandardCharsets.UTF_8),
                            BodyPublishers.ofString(headers, StandardCharsets.UTF_8),
                            part.publisher,
                            BodyPublishers.ofString(CRLF, StandardCharsets.UTF_8)
                    );
                }).collect(Collectors.toList());
        publishers.add(BodyPublishers.ofString(boundary + "--", StandardCharsets.UTF_8));
        this.bodyPublishers = publishers;
    }

    @Override
    public long contentLength() {
        return this.contentLength;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        if (this.subscriber != null) {
            throw new IllegalStateException("A subscriber already subscribed to this publisher.");
        }
        this.subscriber = subscriber;
        this.subscriber.onSubscribe(new Flow.Subscription() {
            @Override
            public void request(long n) {
                if (n <= 0) {
                    throw new IllegalArgumentException("Expected at least 1 requested items");
                }
                boolean paused = requested == 0;
                requested += n;
                if (paused) {
                    requestNext(subscriber);
                }
            }

            @Override
            public void cancel() {
                requested = -1;
                currentSubscription.cancel();
            }
        });
    }

    private void requestNext(Flow.Subscriber<? super ByteBuffer> subscriber) {
        if (this.subscriber == null) {
            throw new IllegalStateException("No subscriber present");
        }

        if (currentlySubscribed) {
            if (this.currentSubscription != null) {
                currentSubscription.request(1);
            }
            return;
        }

        if (this.bodyPublishers.isEmpty()) {
            this.subscriber.onComplete();
            return;
        }
        this.bodyPublishers.get(0).subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                currentSubscription = subscription;
                if (requested-- > 0) {
                    this.subscription.request(1);
                }
            }

            @Override
            public void onNext(ByteBuffer item) {
                subscriber.onNext(item);
                if (requested-- > 0) {
                    this.subscription.request(1);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                subscriber.onError(throwable);
            }

            @Override
            public void onComplete() {
                currentlySubscribed = false;
                bodyPublishers.remove(0);
                requestNext(subscriber);
            }
        });
        this.currentlySubscribed = true;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public static class Part {
        private List<String> headers = new ArrayList<>();

        private final HttpRequest.BodyPublisher publisher;

        Part(HttpRequest.BodyPublisher publisher) {
            this.publisher = publisher;
        }

        private long getHeadersLength() {
            return this.headers.stream().mapToLong(String::length).sum() + CRLF.length() * this.headers.size();
        }

        private long contentLength() {
            return this.getHeadersLength() + CRLF.length() + this.publisher.contentLength() + CRLF.length();
        }

        private Part addHeader(String key, String value) {
            this.headers.add(key + ": " + value);
            return this;
        }

        public static Part forFormData(String key, String value) {
            return new Part(BodyPublishers.ofString(value, StandardCharsets.UTF_8))
                    .addHeader("Content-Disposition", "form-data; name=" + key);
        }

        public static Part forBodyPublisher(String key, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
            return forBodyPublisher(key, null, contentType, bodyPublisher);
        }

        public static Part forBodyPublisher(String key, String filename, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
            String disposition = "form-data; name=" + key;
            if (filename != null) {
                disposition += "; filename=" + filename;
            }
            return new Part(bodyPublisher)
                    .addHeader("Content-Disposition", disposition)
                    .addHeader("Content-Type", contentType);
        }
    }

    public static class Builder {
        private String boundaryDelimiter = UUID.randomUUID().toString();

        private List<Part> part = new ArrayList<>();

        public Builder addPart(Part part) {
            this.part.add(part);
            return this;
        }

        public Builder setBoundaryDelimiter(String boundaryDelimiter) {
            this.boundaryDelimiter = boundaryDelimiter;
            return this;
        }

        public MultipartBodyPublisher build() {
            return new MultipartBodyPublisher(this.boundaryDelimiter, this.part);
        }
    }
}
