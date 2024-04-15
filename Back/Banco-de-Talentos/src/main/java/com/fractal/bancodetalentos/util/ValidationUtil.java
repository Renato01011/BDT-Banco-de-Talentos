package com.fractal.bancodetalentos.util;

import java.util.function.Consumer;

public final class ValidationUtil {

    public static <T, R> void setNonNullValue(R value, Consumer<T> setter) {
        if (value != null) {
            setter.accept( (T) value);
        }
    }

}
