package com.jtelegram.jtelegraf;

import com.jtelegram.api.update.Update;
import com.jtelegram.api.update.UpdateContents;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nick Robson
 */
@Getter
@AllArgsConstructor
public class TelegrafUpdateContext<T extends Update<U>, U extends UpdateContents> {

    @Nonnull
    private final T update;

    @Nonnull
    public U getContents() {
        return update.getContents();
    }

}
