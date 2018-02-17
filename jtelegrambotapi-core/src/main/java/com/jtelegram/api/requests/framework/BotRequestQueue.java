package com.jtelegram.api.requests.framework;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
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
    private OkHttpClient client;

    public BotRequestQueue(OkHttpClient client) {
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
