package com.jtelegram.api.commands.filters;

import com.jtelegram.api.chat.ChatType;
import com.jtelegram.api.commands.Command;
import com.jtelegram.api.events.message.TextMessageEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * A {@link CommandFilter} testing which type of chat the
 * command was executed in.
 *
 * @author Nick Robson
 */
public class ChatTypeFilter extends AbstractCommandFilter {

    private final Set<ChatType> chatTypes;

    /**
     * Creates a ChatTypeFilter with given children.
     *
     * @param chatType The ONLY type of chat to allow commands from
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public ChatTypeFilter(ChatType chatType, CommandFilter... children) {
        super(children);
        this.chatTypes = Collections.singleton(chatType);
    }

    /**
     * Creates a ChatTypeFilter with given children.
     *
     * @param chatTypes The types of chat to allow commands from
     * @param children The children filters, which will be checked in order
     *                 if this filter tests to be {@code true}
     */
    public ChatTypeFilter(ChatType[] chatTypes, CommandFilter... children) {
        super(children);
        this.chatTypes = EnumSet.copyOf(Arrays.asList(chatTypes));
    }

    @Override
    protected boolean preTest(TextMessageEvent event, Command command) {
        ChatType type = event.getMessage().getChat().getType();
        return this.chatTypes.contains(type);
    }

}
