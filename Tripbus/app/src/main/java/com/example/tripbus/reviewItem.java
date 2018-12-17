package com.example.tripbus;

public class reviewItem {
    private String userId;
    private float userStar;
    private String userText;

    public reviewItem(String ui, float us, String ut){
        this.userId = ui;
        this.userStar = us;
        this.userText = ut;
    }

    public String getUserId() { return userId; }

    public float getUserStar() { return userStar; }

    public String getUserText() { return userText; }
}
