package com.mziuri.message;

public class MessageValidator {
    private MessageValidator instance;

    public synchronized MessageValidator getInstance() {
        if (instance == null) {
            instance = new MessageValidator();
        }
        return instance;
    }

    public boolean validateMessage(String message) {
        return message != null && message.trim().isEmpty();
    }
}
