package io.jtelegram.api.requests.framework;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BotRequestQueue extends Thread {
    @Getter
    private final Queue<BotRequest> requestQueue = new ConcurrentLinkedQueue<>();
    @Getter
    @Setter
    // the default interval between requests
    // 0 for instantaneous, negative numbers
    // will result in an error
    private long interval = 100;
    @Setter
    @Getter
    private OkHttpClient client;

    public BotRequestQueue(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            BotRequest request = requestQueue.poll();

            if (request == null) {
                continue;
            }

            try {
                Request httpRequest = request.getRequest().build(request.getBot()).build();
                Response response = client.newCall(httpRequest).execute();

                request.getRequest().handleResponse(response);
            } catch (IOException ex) {
                request.getRequest().handleException(ex);
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
