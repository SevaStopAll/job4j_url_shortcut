package ru.job4j.url_shortcut.utils;

import java.util.Random;

public class Generator {
    public static String generate() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static void main(String[] args) {
        System.out.println(Generator.generate());
    }
}
