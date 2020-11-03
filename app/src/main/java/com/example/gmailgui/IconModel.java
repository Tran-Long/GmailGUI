package com.example.gmailgui;

public class IconModel {
    char iconLetter;
    int bgResource;

    public IconModel(char iconLetter, int bgResource){
        this.bgResource = bgResource;
        this.iconLetter = iconLetter;
    }

    public int getBgResource() {
        return bgResource;
    }

    public char getIconLetter() {
        return iconLetter;
    }

    public void setBgResource(int bgResource) {
        this.bgResource = bgResource;
    }

    public void setIconLetter(char iconLetter) {
        this.iconLetter = iconLetter;
    }
}
