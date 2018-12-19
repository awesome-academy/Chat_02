package com.framgia.f_talk.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
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

    public static String makePrivateChatDir(String uid1, String uid2) {
        return (uid1.compareTo(uid2) > 0) ?
                append(uid1, Constant.PRIVATE_MESSAGE_DIR_CONNECTION_SYNTAX, uid2) :
                append(uid2, Constant.PRIVATE_MESSAGE_DIR_CONNECTION_SYNTAX, uid1);
    }

    public static String makeTimeStamp(long timeStamp) {
        long duration = Calendar.getInstance().getTimeInMillis() - timeStamp;
        int day = (int) TimeUnit.MILLISECONDS.toDays(duration);
        if (day != 0) return append(String.valueOf(day), Constant.TIME_STAMP_DAY);
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        if (hours != 0) return append(String.valueOf(hours), Constant.TIME_STAMP_HOUR);
        long minute = TimeUnit.MILLISECONDS.toMinutes(duration);
        if (minute != 0) return append(String.valueOf(minute), Constant.TIME_STAMP_MIN);
        return Constant.TIME_JUST_NOW;
    }
}
