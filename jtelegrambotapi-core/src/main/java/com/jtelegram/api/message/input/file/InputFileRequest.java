package com.jtelegram.api.message.input.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.util.MultipartBodyPublisher;

import java.net.http.HttpRequest;
import java.util.List;

public interface InputFileRequest {
    String OCTET_STREAM_TYPE = "application/octet-stream";

    List<InputFile> getInputFiles();

    HttpRequest.Builder superBuild(TelegramBot bot);

    default HttpRequest.Builder getBuilder(TelegramBot bot) {
        List<InputFile> inputFiles = getInputFiles();

        if (inputFiles.stream().noneMatch(InputFile::isAttachable)) {
            return superBuild(bot);
        }

        JsonObject obj = TelegramBotRegistry.GSON.toJsonTree(this).getAsJsonObject();
        MultipartBodyPublisher.Builder bodyBuilder = new MultipartBodyPublisher.Builder();

        obj.keySet().forEach((key) -> {
            JsonElement e = obj.get(key);
            String val;

            if (e.isJsonPrimitive()) {
                val = e.getAsString();
            } else {
                val = e.toString();
            }

            bodyBuilder.addPart(MultipartBodyPublisher.Part.forFormData(key, val));
        });

        inputFiles.stream().filter(InputFile::isAttachable).forEach((inputFile) -> inputFile.attachTo(bodyBuilder));
        return superBuild(bot).header("Content-Type", "multipart/form-data").POST(bodyBuilder.build());
    }
}
