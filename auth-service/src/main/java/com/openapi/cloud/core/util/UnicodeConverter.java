package com.openapi.cloud.core.util;

import java.io.*;
import java.util.Properties;

public class UnicodeConverter {
    public static String toUnicodeEscape(String input) {
        StringBuilder unicode = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c < 128) {
                unicode.append(c);
            } else {
                // Remove the extra backslash
                unicode.append("\\u")
                        .append(String.format("%04x", (int) c));
            }
        }
        return unicode.toString();
    }

    public static void saveToProperties(String key, String value, String fileName) {
        Properties props = new Properties();
        props.setProperty(key, toUnicodeEscape(value));

        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(fileName), "ISO-8859-1")) {
            props.store(writer, null);
        } catch (IOException e) {
           throw  new RuntimeException("Error saving properties file", e);
        }
    }

    public static void main(String[] args) {
        String japaneseText = "名前は必須です。";
        saveToProperties("error", japaneseText, "messages.properties");

        // To verify the conversion
        try (Reader reader = new InputStreamReader(
                new FileInputStream("messages.properties"), "ISO-8859-1")) {
            Properties props = new Properties();
            props.load(reader);
            System.out.println("Stored value: " + props.getProperty("error"));
        } catch (IOException e) {
           throw new RuntimeException("Error reading properties file", e);
        }
    }
}

