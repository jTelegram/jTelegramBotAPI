package com.jtelegram.api.requests.framework;

import com.google.gson.*;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.ex.InvalidResponseException;
import com.jtelegram.api.ex.NetworkException;
import com.jtelegram.api.ex.TelegramApiException;
import com.jtelegram.api.ex.TelegramException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTelegramRequest implements TelegramRequest {
    // utility field
    protected transient static Gson gson = TelegramBotRegistry.GSON;
    private transient final String endPoint;
    @Setter
    protected transient Consumer<TelegramException> errorHandler;

    protected void handleError(TelegramException ex) {
        if (errorHandler == null) {
            System.out.println("Uncaught exception for " + getClass().getSimpleName() + "...");
            ex.printStackTrace();
            return;
        }

        errorHandler.accept(ex);
    }

    protected String getBody(Response response) throws IOException {
        return response == null ? null : response.body().string();
    }

    protected JsonElement validate(String response) {
        JsonParser parser = new JsonParser();
        JsonElement jsonResponse;

        try {
            jsonResponse = parser.parse(response);
        } catch (JsonSyntaxException ex) {
            handleError(new InvalidResponseException(
                    "JSON Syntax Error: " + ex.getMessage(),
                    response
            ));

            return null;
        }

        if (jsonResponse.isJsonObject()) {
            JsonObject object = jsonResponse.getAsJsonObject();

            if (!object.get("ok").getAsBoolean()) {
                // todo convert to good exceptions
                handleError(gson.fromJson(response, TelegramApiException.class));
                return null;
            }

            return object.get("result");
        }

        return null;
    }

    @Override
    public String getEndPoint() {
        return endPoint;
    }

    @Override
    public String serialize() {
        return gson.toJson(this);
    }

    @Override
    public void handleException(IOException ex) {
        handleError(new NetworkException(ex));
    }
}
