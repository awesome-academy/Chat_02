package com.framgia.f_talk.util;

import java.util.regex.Matcher;

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

    public static boolean isEmailValid(String email) {
        Matcher matcher = Constant.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= Constant.MIN_PASSWORD_LENGTH;
    }

    public static boolean isFullNameValid(String fullName) {
        return fullName.length() != 0;
    }
}
