package com.saadahmedsoft.sparkconvo.util.common;

import java.util.Random;

public class RandomStringUtil {

    private static final String TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(26);
            stringBuilder.append(TEXT.charAt(index));
        }

        return stringBuilder.toString();
    }
}
