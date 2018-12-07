package com.framgia.f_talk.util;

import java.util.regex.Pattern;

public class Constant {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);
    public static final int MIN_PASSWORD_LENGTH = 6;
    private Constant() {
    }
}
