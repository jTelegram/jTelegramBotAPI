package io.jtelegram.api.message.sendable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jtelegram.api.TelegramBot;
import io.jtelegram.api.chat.id.ChatId;
import io.jtelegram.api.ex.TelegramException;
import io.jtelegram.api.message.sendable.input.file.InputFile;
import io.jtelegram.api.message.sendable.input.file.LocalInputFile;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.util.function.Consumer;

public abstract class InputFileMessageRequest<T> extends SendableMessageRequest<T> {
    private static final MediaType OCTET_STREAM_TYPE = MediaType.parse("application/octet-stream");

    protected InputFileMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
    }

    @Override
    public Request.Builder build(TelegramBot bot) {
        InputFile file = getInputFile();

        if (!(file instanceof LocalInputFile)) {
            return super.build(bot);
        }

        /*
         * If we are uploading a file,
         * we have to send our request
         * completely as a multi part
         * for request...
         */
        File inputFile = ((LocalInputFile) file).getData();
        JsonObject obj = gson.toJsonTree(this).getAsJsonObject();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        obj.keySet().forEach((key) -> {
            JsonElement e = obj.get(key);
            String val;

            if (e.isJsonPrimitive()) {
                val = e.getAsString();
            } else {
                val = e.getAsJsonObject().toString();
            }

            if (!val.startsWith("file://")) {
                bodyBuilder.addFormDataPart(key, val);
            } else {
                // this is our input file, add it to the form accordingly
                bodyBuilder.addFormDataPart(
                        key,
                        inputFile.getName(),
                        RequestBody.create(OCTET_STREAM_TYPE, inputFile)
                );
            }
        });

        return super.build(bot).post(bodyBuilder.build());
    }

    public abstract InputFile getInputFile();
}
