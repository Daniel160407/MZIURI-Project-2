package com.mziuri.message;

public class MessageValidator {
    private static MessageValidator instance;

    public static synchronized MessageValidator getInstance() {
        if (instance == null) {
            instance = new MessageValidator();
        }
        return instance;
    }

    public boolean validateMessage(String message) {
        return message != null && !message.contains("\n");
    }
}
