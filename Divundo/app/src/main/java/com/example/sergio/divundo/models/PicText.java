package com.example.sergio.divundo.models;

public class PicText {

    private String text;
    private String picture;

    public PicText(){}

    public PicText(String text, String picture) {
        this.text = text;
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
