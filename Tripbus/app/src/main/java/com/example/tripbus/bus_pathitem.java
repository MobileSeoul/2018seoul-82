package com.example.tripbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class bus_pathitem {

    private String rcmnd_placetxt;
    private String rcmnd_placeimg;

    public String getRcmnd_placetxt() {
        return rcmnd_placetxt;
    }

    public void setRcmnd_placetxt(String rcmnd_placetxt) {
        this.rcmnd_placetxt = rcmnd_placetxt;
    }

    public String getRcmnd_placeimg() {
        return rcmnd_placeimg;
    }

    public void setRcmnd_placeimg(String rcmnd_placeimg) {
        this.rcmnd_placeimg = rcmnd_placeimg;
    }

    public bus_pathitem(String pt, String pi){
        this.rcmnd_placetxt = pt;
        this.rcmnd_placeimg = pi;
    }

    public String getTxt() { return rcmnd_placetxt; }

    public String getImg() { return rcmnd_placeimg; }

}
