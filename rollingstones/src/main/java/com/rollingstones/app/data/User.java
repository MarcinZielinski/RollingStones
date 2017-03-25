package com.rollingstones.app.data;

/**
 * Created by Marcin on 2017-03-25.
 */
public class User {
    private int hash;
    private String mac;


    public User(int hash, String mac) {
        this.hash = hash;
        this.mac = mac;
    }

    public int getHash(String mac) {
        return hash;
    }

    public String getMac(int hash) {
        return mac;
    }
}
