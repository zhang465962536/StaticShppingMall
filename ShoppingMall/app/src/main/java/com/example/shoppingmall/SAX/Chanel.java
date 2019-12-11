package com.example.shoppingmall.SAX;

public class Chanel {

    private String  chanelid;
    private String image;
    private String text;

    public Chanel() { }

    public Chanel(String chanelid, String image, String text) {
        this.chanelid = chanelid;
        this.image = image;
        this.text = text;
    }

    public String getChanelid() {
        return chanelid;
    }

    public void setChanelid(String chanelid) {
        this.chanelid = chanelid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Chanel{" +
                "chanelid='" + chanelid + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
