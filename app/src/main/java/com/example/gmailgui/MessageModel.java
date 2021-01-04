package com.example.gmailgui;

import java.io.Serializable;
import java.util.Random;

public class MessageModel implements Serializable {
    String sender;
    String peakContent;
    String subject;
    String day;
    int bgResource;
    char firstLetter;
    boolean checkFavourite;

    public MessageModel(String sender, String subject, String peakContent){
        this.sender = sender;
        this.subject = subject;
        this.peakContent = peakContent;
        this.checkFavourite = false;
        this.firstLetter = sender.charAt(0);
        Random random = new Random();
        if(random.nextInt() %5 == 0){
            this.bgResource = R.drawable.round_background_blue;
        }else if(random.nextInt() %5 == 1){
            this.bgResource = R.drawable.round_background_green;
        }else if(random.nextInt() %5 == 2){
            this.bgResource = R.drawable.round_background_grey;
        }else if(random.nextInt() %5 == 3){
            this.bgResource = R.drawable.round_background_red;
        }else
            this.bgResource = R.drawable.round_background_purple;
    }

//    public IconModel getSenderIcon() {
//        return senderIcon;
//    }

    public String getSubject() {
        return subject;
    }

    public String getPeakContent() {
        return peakContent;
    }

    public String getSender() {
        return sender;
    }

    public int getBgResource() {
        return bgResource;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public boolean isCheckFavourite() {
        return checkFavourite;
    }

    public void setPeakContent(String peakContent) {
        this.peakContent = peakContent;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setBgResource(int bgResource) {
        this.bgResource = bgResource;
    }

    public void setCheckFavourite(boolean checkFavourite) {
        this.checkFavourite = checkFavourite;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    //    public void setSenderIcon(IconModel senderIcon) {
//        this.senderIcon = senderIcon;
//    }
}
