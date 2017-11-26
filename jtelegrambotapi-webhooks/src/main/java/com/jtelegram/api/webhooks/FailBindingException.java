package com.jtelegram.api.webhooks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FailBindingException extends RuntimeException {
    private Throwable cause;
}
