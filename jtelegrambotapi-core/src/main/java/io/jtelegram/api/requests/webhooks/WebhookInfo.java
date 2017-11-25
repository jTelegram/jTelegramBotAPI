package io.jtelegram.api.requests.webhooks;

import io.jtelegram.api.update.UpdateType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(of = "url")
public class WebhookInfo {
    private String url;
    private boolean hasCustomCertificate;
    private int pendingUpdateCount;
    private long lastErrorDate;
    private Integer maxConnections;
    private List<UpdateType> allowedUpdates;
}
