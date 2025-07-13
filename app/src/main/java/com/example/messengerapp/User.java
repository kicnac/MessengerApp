package com.example.messengerapp;

public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private int avatarResId;

    public User(String id, String name, String phoneNumber, int avatarResId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatarResId = avatarResId;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getAvatarResId() { return avatarResId; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }
} 