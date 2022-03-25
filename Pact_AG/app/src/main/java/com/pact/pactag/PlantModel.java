package com.pact.pactag;

public class PlantModel {
    public final String mName;
    public final String mDescription;
    public final String mUrlImage;
    public final Boolean mNotif;

    public PlantModel(String name, String description, String urlImage, Boolean notif) {
        this.mName = name;
        this.mDescription = description;
        this.mUrlImage = urlImage;
        this.mNotif = notif;
    }

}

