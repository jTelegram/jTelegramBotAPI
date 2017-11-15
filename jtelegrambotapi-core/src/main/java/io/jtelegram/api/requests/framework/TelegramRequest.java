package io.jtelegram.api.requests.framework;


import io.jtelegram.api.TelegramBot;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public interface TelegramRequest {
    MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * Use GSON to properly serialize the request
     * to send to Telegram's servers. This method
     * exists if there are any special cases or
     * pre-processing required.
     *
     * @return the serialized JSON string
     */
    String serialize();

    default Request.Builder build(TelegramBot bot) {
        return new Request.Builder()
                .url(bot.getRegistry().getApiUrl() + bot.getApiKey() + "/" + getEndPoint())
                .post(RequestBody.create(JSON_MEDIA_TYPE, serialize()));
    }

    /**
     * Get the appropriate endpoint for this request.
     */
    String getEndPoint();

    /**
     * Handle the response from the server, call any
     * relevant callbacks and do error validation
     */
    void handleResponse(Response response) throws IOException;

    /**
     * Handle an exception in the case that a network error occured
     */
    void handleException(IOException ex);
}
