package io.jtelegram.api.update;

import io.jtelegram.api.inline.InlineQuery;
import io.jtelegram.api.inline.result.ChosenInlineResult;
import io.jtelegram.api.message.Message;
import io.jtelegram.api.message.payments.PreCheckoutQuery;
import io.jtelegram.api.message.payments.ShippingQuery;
import lombok.Getter;

@Getter
public class Update {
    private int updateId;

    @Getter
    public static class ChannelPostUpdate extends Update {
        private Message channelPost;
    }

    @Getter
    public static class ChosenInlineResultUpdate extends Update {
        private ChosenInlineResult chosenInlineResult;
    }

    @Getter
    public static class EditedChannelPostUpdate extends Update {
        private Message editedChannelPost;
    }

    @Getter
    public static class EditedMessageUpdate extends Update {
        private Message editedMessage;
    }

    @Getter
    public static class InlineQueryUpdate extends Update {
        private InlineQuery inlineQuery;
    }

    @Getter
    public static class MessageUpdate extends Update {
        private Message message;
    }

    @Getter
    public static class PreCheckoutQueryUpdate extends Update {
        private PreCheckoutQuery preCheckoutQuery;
    }

    @Getter
    public static class ShippingQueryUpdate extends Update {
        private ShippingQuery shippingQuery;
    }
}
