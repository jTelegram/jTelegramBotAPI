package com.jtelegram.api.ex;

import lombok.ToString;

/**
 * This exception is called when the API returns
 * a non-JSON response
 */
@ToString(callSuper = true)
public class InvalidResponseException extends TelegramException {
}
