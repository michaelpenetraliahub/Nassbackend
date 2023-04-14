package com.jtb.taxpayerws.utils;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String OTHER_PUNCTUATION = "!@#&()–[{}]:;',?*";
    private static final String OTHER_SYMBOL = "~$^+=";
    private static final String OTHER_SPECIAL = OTHER_PUNCTUATION + OTHER_SYMBOL;
    private static final int PASSWORD_LENGTH = 10;

    private static final String PASSWORD_ALLOW = CHAR_LOWERCASE + CHAR_UPPERCASE + DIGIT + OTHER_SPECIAL;

    private static final SecureRandom random = new SecureRandom();


    public static String generateStrongPassword() {

        StringBuilder result = new StringBuilder(PASSWORD_LENGTH);

        // at least 2 chars (lowercase)
        String strLowerCase = randomizeString(CHAR_LOWERCASE, 2);
        //System.out.format("%-20s: %s%n", "Chars (Lowercase)", strLowerCase);
        result.append(strLowerCase);

        // at least 2 chars (uppercase)
        String strUppercaseCase = randomizeString(CHAR_UPPERCASE, 2);
        //System.out.format("%-20s: %s%n", "Chars (Uppercase)", strUppercaseCase);
        result.append(strUppercaseCase);

        // at least 2 digits
        String strDigit = randomizeString(DIGIT, 2);
        //System.out.format("%-20s: %s%n", "Digits", strDigit);
        result.append(strDigit);

        // at least 2 special characters (punctuation + symbols)
        String strSpecialChar = randomizeString(OTHER_SPECIAL, 2);
        //System.out.format("%-20s: %s%n", "Special chars", strSpecialChar);
        result.append(strSpecialChar);

        // remaining, just random
        String strOther = randomizeString(PASSWORD_ALLOW, PASSWORD_LENGTH - 8);
        //System.out.format("%-20s: %s%n", "Others", strOther);
        result.append(strOther);

        return result.toString();
    }

    // generate a random char[], based on `input`
    private static String randomizeString(String input, int size) {

        if (input == null || input.length() <= 0)
            throw new IllegalArgumentException("Invalid input.");
        if (size < 1) throw new IllegalArgumentException("Invalid size.");

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // produce a random order
            int index = random.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }

}