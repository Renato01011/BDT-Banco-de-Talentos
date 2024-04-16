package com.fractal.bancodetalentos.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

public final class ValidationUtil {

    public static <T, R> void setNonNullValue(R value, Consumer<T> setter) {
        if (value != null) {
            setter.accept( (T) value);
        }
    }

    public static <T> boolean allFieldsValid(List<T> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }

        for (T obj : list) {
            if (obj == null || !checkFields(obj)) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean checkFields(T obj) {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getType() == String.class) {
                    String value = (String) field.get(obj);
                    if (value == null || value.isEmpty()) {
                        return false;
                    }
                } else {
                    Object value = field.get(obj);
                    if (value == null) {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


}
