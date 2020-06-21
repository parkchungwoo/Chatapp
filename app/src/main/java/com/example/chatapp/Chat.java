package com.example.chatapp;

import java.util.HashMap;
import java.util.Map;

public class Chat {
    public Map<String,Boolean> users = new HashMap<>(); // 채팅방의 유저들
    public Map<String,Comment> comments = new HashMap<>(); //채팅방의 내용

    public static class Comment {
        public String uid;
        public String message;
    }

    String email;
    String text;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
