package com.txzing.spinnertest.model;

public class Hero {
    private int hIcon;
    private String hname;

    public Hero(int hIcon, String hname) {
        this.hIcon = hIcon;
        this.hname = hname;
    }

    public int gethIcon() {
        return hIcon;
    }

    public void sethIcon(int hIcon) {
        this.hIcon = hIcon;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }
}
