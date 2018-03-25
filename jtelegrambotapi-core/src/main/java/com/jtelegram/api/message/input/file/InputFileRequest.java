package com.jtelegram.api.message.input.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;

public interface InputFileRequest {
    MediaType OCTET_STREAM_TYPE = MediaType.parse("application/octet-stream");

    List<InputFile> getInputFiles();

    Request.Builder superBuild(TelegramBot bot);

    default Request.Builder getBuilder(TelegramBot bot) {
        List<InputFile> inputFiles = getInputFiles();

        if (inputFiles.stream().noneMatch(InputFile::isAttachable)) {
            return superBuild(bot);
        }

        JsonObject obj = TelegramBotRegistry.GSON.toJsonTree(this).getAsJsonObject();
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

        inputFiles.stream().filter(InputFile::isAttachable).forEach((inputFile) -> inputFile.attachTo(bodyBuilder));
        return superBuild(bot).post(bodyBuilder.build());
    }
}
