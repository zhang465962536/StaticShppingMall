package com.example.shoppingmall.Type.Fragment;

import java.io.Serializable;

public class Goods implements Serializable {

    private String gid;
    private String gname;
    private String price;
    private String address;
    private String url;
    private String category;
    private String options;
    private String select1;
    private String select2;
    private String select3;

    public Goods() {
    }

    public Goods(String gid, String gname, String price, String address, String url, String category, String options, String select1, String select2, String select3) {
        this.gid = gid;
        this.gname = gname;
        this.price = price;
        this.address = address;
        this.url = url;
        this.category = category;
        this.options = options;
        this.select1 = select1;
        this.select2 = select2;
        this.select3 = select3;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOpttions() {
        return options;
    }

    public void setOpttions(String opttions) {
        this.options = opttions;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }

    public String getSelect3() {
        return select3;
    }

    public void setSelect3(String select3) {
        this.select3 = select3;
    }
}
