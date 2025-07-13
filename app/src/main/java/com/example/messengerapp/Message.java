package com.example.messengerapp;

public class Message {
    private String senderId;
    private String senderName;
    private String text;
    private long timestamp;
    private int avatarResId;
    private boolean isFromCurrentUser;

    public Message(String senderId, String senderName, String text, long timestamp, int avatarResId, boolean isFromCurrentUser) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.text = text;
        this.timestamp = timestamp;
        this.avatarResId = avatarResId;
        this.isFromCurrentUser = isFromCurrentUser;
    }

    // Getters
    public String getSenderId() { return senderId; }
    public String getSenderName() { return senderName; }
    public String getText() { return text; }
    public long getTimestamp() { return timestamp; }
    public int getAvatarResId() { return avatarResId; }
    public boolean isFromCurrentUser() { return isFromCurrentUser; }

    // Setters
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public void setText(String text) { this.text = text; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }
    public void setFromCurrentUser(boolean fromCurrentUser) { isFromCurrentUser = fromCurrentUser; }
} 