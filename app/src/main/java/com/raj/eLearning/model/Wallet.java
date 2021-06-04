package com.raj.eLearning.model;

public class Wallet {
    private int id;
    private String userId;
    private int coin;

    public Wallet() {
    }

    public Wallet(int id, String userId, int coin) {
        this.id = id;
        this.userId = userId;
        this.coin = coin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
