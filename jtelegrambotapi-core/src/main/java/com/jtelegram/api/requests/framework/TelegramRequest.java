package com.jtelegram.api.requests.framework;

import com.google.gson.JsonElement;
import com.jtelegram.api.TelegramBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public interface TelegramRequest<T> {
    /**
     * Use GSON to properly serialize the request
     * to send to Telegram's servers. This method
     * exists if there are any special cases or
     * pre-processing required.
     *
     * @return the serialized JSON string
     */
    String serialize();

    default HttpRequest.Builder build(TelegramBot bot) {

        return HttpRequest.newBuilder()
                .uri(URI.create(bot.getRegistry().getApiUrl() + bot.getApiKey() + "/" + getEndPoint()))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(serialize(), StandardCharsets.UTF_8));
    }

    /**
     * Get the appropriate endpoint for this request.
     *
     * @return The endpoint
     */
    String getEndPoint();

    /**
     * Handle the response from the server, call any
     * relevant callbacks and do error validation
     *
     * @param response The response
     *
     * @throws IOException If any I/O error occurred
     */
    void handleResponse(HttpResponse<String> response) throws IOException;

    /**
     * Handle an exception in the case that an I/O error occured
     *
     * @param ex The exception
     */
    void handleException(IOException ex);

    /*
     * In the case that the request is not successful, the
     * request must handle the error parameters returned.
     * This is especially useful for sending messages.
     *
     * @return Whether or not to call the error handler
     * @see ResponseParameters
     */
    //boolean handleResponseParameters(ResponseParameters parameters);
}
