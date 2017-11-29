package com.jtelegram.api.test;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.commands.CommandHandler;
import com.jtelegram.api.commands.filters.TextFilter;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.requests.message.framework.ParseMode;
import com.jtelegram.api.requests.message.send.SendText;
import com.jtelegram.api.test.message.LiveLocationTest;
import com.jtelegram.api.test.message.LocationMessageTest;
import com.jtelegram.api.update.PollingUpdateProvider;
import com.jtelegram.api.update.UpdateProvider;
import com.jtelegram.api.webhooks.WebhookUpdateProvider;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.JksOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BotMainTest {
    private TelegramBotRegistry registry;
    private TelegramBot bot;
    private final Map<String, TestModule> modules = new HashMap<>();

    private BotMainTest(String[] args) throws Exception {
        UpdateProvider provider;

        if (args[1].equalsIgnoreCase("webhooks")) {
            provider = WebhookUpdateProvider.builder()
                    .serverOptions(new HttpServerOptions().setSsl(true)
                            .setKeyStoreOptions(new JksOptions().setPath("bot.jks").setPassword("aaaaaa"))
                            .setPort(443)
                            .setHost(args[2]))
                    .selfSignedCertificate(new File("bot-certificate.pem"))
                    .build();
        } else {
            provider = new PollingUpdateProvider();
        }

        this.registry = TelegramBotRegistry.builder()
                .updateProvider(provider)
                .build();

        registry.registerBot(args[0], (bot, error) -> {
            if (error != null) {
                System.out.println("Could not log in!");
                error.printStackTrace();
                System.exit(-1);
                return;
            }

            this.bot = bot;

            System.out.printf("Logged in as @%s\n", bot.getBotInfo().getUsername());

            registerModules();
            bot.getCommandRegistry().registerCommand(new TextFilter("test", false, this::handleTestCommand));
        });
    }

    public static void main(String[] args) throws Exception {
        new BotMainTest(args);
    }

    private boolean handleTestCommand(TextMessageEvent event, Command command) {
        List<String> args = command.getArgs();

        if (args.size() == 0) {
            bot.perform(SendText.builder()
                    .text("Please specify a module to test, or 'all' to perform all applicable tests for this chat")
                    .chatId(ChatId.of(command.getBaseMessage().getChat()))
                    .build());
            return true;
        }

        String moduleName = args.get(0);
        String[] moduleArgs = (args.size() > 1 ? args.subList(1, args.size()) : new ArrayList<String>()).toArray(new String[0]);

        if ("all".equalsIgnoreCase(moduleName)) {
            List<TestModule> applicableModules = modules.values().stream()
                    .filter((m) -> m.validate(command) == null)
                    .collect(Collectors.toList());

            applicableModules.forEach((m) -> testModule(m, moduleArgs, command));
            return true;
        }

        TestModule module = modules.get(moduleName.toLowerCase());

        if (module != null) {
            testModule(module, moduleArgs, command);
            return true;
        }

        sendError(command, "No module found by the name of " + moduleName);
        return true;
    }

    private void testModule(TestModule module, String[] args, Command command) {
        String error = module.validate(command);

        if (error != null) {
            sendError(command, "Test cannot be performed due to " + error);
            return;
        }

        try {
            module.handle(args, command);
        } catch (Exception ex) {
            sendError(command, "Test failed due to " + ex.getClass().getSimpleName() + "! Check logs for more details");
            ex.printStackTrace();
        }
    }

    private void sendError(Command command, String error) {
        bot.perform(SendText.builder()
                .parseMode(ParseMode.MARKDOWN)
                .text("*ERROR:* " + error)
                .chatId(ChatId.of(command.getBaseMessage().getChat()))
                .build());
    }

    private void registerModule(TestModule module) {
        modules.put(module.getName(), module);
    }

    private void registerModules() {
        registerModule(new LiveLocationTest(bot));
        registerModule(new LocationMessageTest(bot));
    }
}
