package com.mziuri.message;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public record Message(String username, String message) {
    public static String messageListToJson(List<Message> messages) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(messages);
    }
}
