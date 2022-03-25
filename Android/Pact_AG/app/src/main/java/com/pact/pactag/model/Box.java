package com.pact.pactag.model;

import java.util.List;

public class Box {
    private final String mNomBox;
    private List<Integer> mListDiag;
    private List<Integer> mListHum;
    private List<Integer> mListLum;

    public List<Integer> getListDiag() {
        return mListDiag;
    }

    public void setListDiag(List<Integer> listDiag) {
        mListDiag = listDiag;
    }

    public List<Integer> getListHum() {
        return mListHum;
    }

    public void setListHum(List<Integer> listHum) {
        mListHum = listHum;
    }

    public List<Integer> getListLum() {
        return mListLum;
    }

    public void setListLum(List<Integer> listLum) {
        mListLum = listLum;
    }

    public Box(String nomBox, List<Integer> listDiag, List<Integer> listHum, List<Integer> listLum) {
        mNomBox = nomBox;
        mListDiag = listDiag;
        mListHum = listHum;
        mListLum = listLum;
    }
}
