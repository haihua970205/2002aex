package com.example.test_kao.ui.me;

import io.realm.RealmObject;

public class ShoppingBean extends RealmObject {
    private String pic;
    private String name;
    private String pice;

//    public ShoppingBean(String pic, String name, String pice) {
//        this.pic = pic;
//        this.name = name;
//        this.pice = pice;
//    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }
}
