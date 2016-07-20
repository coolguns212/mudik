package com.mudik.pens.mudikapp.model;

/**
 * Created by Rimawanti Fauzyah on 5/19/2016.
 */
public class Place {
    public String p_name;
    public double p_detail;
    public int img;
    public int id;
    public Place(String nama, double detil, int gambar, int id){
        this.p_name = nama;
        this.p_detail= detil;
        this.img = gambar;
        this.id=id;
    }

}
