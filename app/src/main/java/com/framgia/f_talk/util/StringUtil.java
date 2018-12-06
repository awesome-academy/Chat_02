package com.framgia.f_talk.util;

public class StringUtil {
    private StringUtil() {
    }

    public static String append(String... args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }
        return builder.toString();
    }
}
