package com.example.scedulealarm;

import android.graphics.Movie;

import com.example.scedulealarm.item.Content;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class SendData {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("schedule")
    private String schedule;

    @SerializedName("checked")
    private int checked;

    @Override
    public String toString(){
        return "{" +
                "id=" + id +
                ", schedule='" + schedule + '\'' +
                ", checked='" + checked + '\'' +
                '}';
    }
}
