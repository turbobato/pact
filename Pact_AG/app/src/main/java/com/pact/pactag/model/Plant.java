package com.pact.pactag.model;

public class Plant {
    private static String mNomPlant;

    public static void setNomPlant(String nomPlant) {
        mNomPlant = nomPlant;
    }

    public String getNomPlant() {
        return mNomPlant;
    }

    private int mDiag;

    public int getDiag() {
        return mDiag;
    }

    public void setDiag(int diag) {
        mDiag = diag;
    }

    public int getHum() {
        return mHum;
    }

    public void setHum(int hum) {
        mHum = hum;
    }

    public int getLum() {
        return mLum;
    }

    public void setLum(int lum) {
        mLum = lum;
    }

    private int mHum;
    private int mLum;


    public Plant(String nom, int diag, int hum, int lum) {
        mNomPlant = nom;
        mDiag = diag;
        mHum = hum;
        mLum = lum;
    }
}
