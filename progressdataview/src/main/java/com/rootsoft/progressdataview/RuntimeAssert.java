package com.rootsoft.progressdataview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Tomas Verhelst
 * @version 1
 */
public final class RuntimeAssert {

    private RuntimeAssert() {
        //Utility class
    }

    public static void assertTrue(final boolean condition) {
        if (!condition) {
            throw new IllegalStateException("Condition should be true");
        }
    }

    public static void assertFalse(final boolean condition) {
        if (condition) {
            throw new IllegalStateException("Condition should be false");
        }
    }

    @NonNull
    public static <T> T notNull(@Nullable final T value, @NonNull final T defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    @NonNull
    public static <T> T argumentNotNull(@Nullable final T value, @NonNull final String argName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s should not be null", argName));
        }
        return value;
    }

    @NonNull
    public static <T> T notNull(@Nullable final T object) {
        if (object == null) {
            throw new IllegalArgumentException("Should not be null");
        }

        return object;
    }
}