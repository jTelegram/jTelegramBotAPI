package com.jtelegram.api.test.message;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.test.AbstractTestModule;
import com.jtelegram.api.test.TestModule;
import com.jtelegram.api.util.TextBuilder;

public class TextBuilderTest extends AbstractTestModule {
    public TextBuilderTest(TelegramBot bot) {
        super(bot);
    }

    @Override
    public String validate(Command command) {
        return null;
    }

    @Override
    public void handle(String[] args, Command command) throws Exception {
        bot.perform(SendText.builder()
                .chatId(ChatId.of(command.getChat()))
                .text(TextBuilder.create()
                        .plain("Hey ").italics(command.getSender().getFirstName()).plain("!")
                        .newLine()
                        .plain("Did you know that ").bold("jTelegram").plain(" has great support for text formatting")
                        .newLine()
                        .plain("You can ").code("code format").plain(" or ").link("link", "https://google.com/")
                        .plain(" or even ").italics("italicize.")
                        .newLine()
                        .plain("Oh yeah, and don't worry about your input having escaping issues")
                        .newLine()
                        .newLine()
                        .plain("*see, no bold*").newLine()
                        .plain("<b>I'm trying hard...</b>").newLine()
                        .plain("<a href=\"http://myshadywebsite.com/\">Free iPhone X</a>").newLine()
                        .plain("See? nothing.").newLine()
                        .newLine())
                .build());
    }

    @Override
    public String getName() {
        return "text-builder";
    }
}
