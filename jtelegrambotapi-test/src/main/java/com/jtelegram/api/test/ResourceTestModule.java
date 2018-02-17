package com.jtelegram.api.test;

import com.jtelegram.api.TelegramBot;
import lombok.Getter;

import java.io.File;
import java.io.InputStream;

@Getter
public abstract class ResourceTestModule extends AbstractTestModule {
    private String resourceName;

    protected ResourceTestModule(TelegramBot bot, String resourceName) {
        super(bot);
        this.resourceName = resourceName;
    }

    public File getResourceFile() {
        return new File("test-resources", resourceName);
    }

    public InputStream getResourceStream() {
        return getClass().getResourceAsStream(resourceName);
    }
}
