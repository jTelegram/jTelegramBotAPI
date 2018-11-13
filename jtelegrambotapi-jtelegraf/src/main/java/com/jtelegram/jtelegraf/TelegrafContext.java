package com.jtelegram.jtelegraf;

import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import javax.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Nick Robson
 */
@Getter
@RequiredArgsConstructor
public class TelegrafContext<T extends Update<U>, U extends UpdateContents> {

    @Nonnull
    private final T update;

    @Nonnull
    public U getContents() {
        return update.getContents();
    }

}
