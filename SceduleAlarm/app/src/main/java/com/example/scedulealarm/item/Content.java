package com.example.scedulealarm.item;

import android.widget.CheckBox;

public class Content {

    public Content(String data, boolean checkBox) {
        this.data = data;
        this.checkBox = checkBox;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    private String data;
    private boolean checkBox;

}
