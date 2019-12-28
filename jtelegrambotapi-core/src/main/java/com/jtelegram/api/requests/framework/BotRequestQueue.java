package com.jtelegram.api.requests.framework;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class BotRequestQueue extends Thread {
    private final BlockingQueue<BotRequest> requestQueue = new LinkedBlockingQueue<>();
    // the default interval between requests
    // 0 for instantaneous, negative numbers
    // will result in an error
    private long interval = 100;
    private HttpClient client;

    public BotRequestQueue(HttpClient client) {
        super("Bot Request Queue");
        this.client = client;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            BotRequest request;

            try {
                request = requestQueue.poll(Long.MAX_VALUE, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                return;
            }

            if (request == null) { // shouldn't happen but just in case
                continue;
            }

            try {

                HttpRequest httpRequest = request.getRequest().build(request.getBot()).build();
                HttpResponse<String> response = client.send(httpRequest,
                                                                      HttpResponse.BodyHandlers.ofString(
                                                                              StandardCharsets.UTF_8));

                request.getRequest().handleResponse(response);
            } catch (IOException ex) {
                request.getRequest().handleException(ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
