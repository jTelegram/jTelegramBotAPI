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
import java.util.List;
import java.util.function.Consumer;

public abstract class InputFileMessageRequest<T> extends SendableMessageRequest<T> {
    private static final MediaType OCTET_STREAM_TYPE = MediaType.parse("application/octet-stream");

    protected InputFileMessageRequest(String endPoint, Class<T> callbackType, Consumer<T> callback, Consumer<TelegramException> errorHandler, ChatId chatId, Integer replyToMessageId, Boolean disableNotification, ReplyMarkup replyMarkup) {
        super(endPoint, callbackType, callback, errorHandler, chatId, replyToMessageId, disableNotification, replyMarkup);
    }

    @Override
    public Request.Builder build(TelegramBot bot) {
        List<InputFile> inputFiles = getInputFiles();

        if (inputFiles.stream().noneMatch((e) -> e instanceof LocalInputFile)) {
            return super.build(bot);
        }

        JsonObject obj = gson.toJsonTree(this).getAsJsonObject();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        obj.keySet().forEach((key) -> {
            JsonElement e = obj.get(key);
            String val;

            if (e.isJsonPrimitive()) {
                val = e.getAsString();
            } else {
                val = e.toString();
            }

            bodyBuilder.addFormDataPart(key, val);
        });

        inputFiles.stream().filter((e) -> e instanceof LocalInputFile).forEach((inputFile) -> {
            File file = ((LocalInputFile) inputFile).getData();
            bodyBuilder.addFormDataPart(file.getName(), file.getName(), RequestBody.create(OCTET_STREAM_TYPE, file));
        });

        return super.build(bot).post(bodyBuilder.build());
    }

    public abstract List<InputFile> getInputFiles();
}
