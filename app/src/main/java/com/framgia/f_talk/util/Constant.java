package com.framgia.f_talk.util;

import java.util.regex.Pattern;

public class Constant {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);
    public static final int MIN_PASSWORD_LENGTH = 6;

    public static final String PERMISSION_EMAIL = "email";

    public static final String PERMISSION_PUBLIC_PROFILE = "public_profile";
    public static final String EXTRA_FULL_NAME = "com.framgia.ftalk.extras.EXTRA_FULL_NAME";
    public static final String EXTRA_EMAIL = "com.framgia.ftalk.extras.EXTRA_EMAIL";

    public static final String USER_DATABASE_DIR = "users/";
    public static final String GROUP_DATABASE_DIR = "groups/";
    public static final String PRIVATE_MESSAGE_DIR = "message_private";
    public static final String GROUP_MESSAGE_DIR = "message_group";

    private Constant() {
    }
}
