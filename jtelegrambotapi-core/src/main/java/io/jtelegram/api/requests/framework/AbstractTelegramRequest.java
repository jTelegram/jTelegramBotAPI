package io.jtelegram.api.requests.framework;

import com.google.gson.*;
import io.jtelegram.api.TelegramBotRegistry;
import io.jtelegram.api.ex.InvalidResponseException;
import io.jtelegram.api.ex.NetworkException;
import io.jtelegram.api.ex.TelegramException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTelegramRequest implements TelegramRequest {
    // utility field
    protected static Gson gson = TelegramBotRegistry.GSON;
    private transient final String endPoint;
    protected transient final Consumer<TelegramException> errorHandler;

    protected String getBody(Response response) throws IOException {
        return response == null ? null : response.body().string();
    }

    protected JsonElement validate(String response) throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement jsonResponse;

        try {
            jsonResponse = parser.parse(response);
        } catch (JsonSyntaxException ex) {
            if (errorHandler != null) {
                errorHandler.accept(new InvalidResponseException());
            }

            return null;
        }

        if (jsonResponse.isJsonObject()) {
            JsonObject object = jsonResponse.getAsJsonObject();

            if (!object.get("ok").getAsBoolean()) {
                // todo convert to good exceptions
                if (errorHandler != null) {
                    errorHandler.accept(gson.fromJson(response, TelegramException.class));
                }
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
        if (errorHandler != null) {
            errorHandler.accept(new NetworkException(ex));
        }
    }
}
