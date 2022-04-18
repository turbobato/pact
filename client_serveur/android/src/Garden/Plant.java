package Garden;

import com.google.gson.Gson;

import java.awt.*;

public class Plant {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String name;
    private int age;
    private int state;
    private Image image;
    private BoxAg diagnostic;
    private BoxAg water;
    private BoxAg light;



    // Serializing and deserializing
    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public Plant deserialization(String jsonInput){
        Plant plant = new Gson().fromJson(jsonInput, Plant.class);
        return plant;
    }

    // Getters


    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getSort() {
        return sort;
    }

    public String getUser_created() {
        return user_created;
    }

    public String getUser_updated() {
        return user_updated;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getState() {
        return state;
    }

    public Image getImage() {
        return image;
    }

    public BoxAg getDiagnostic() {
        return diagnostic;
    }

    public BoxAg getWater() {
        return water;
    }

    public BoxAg getLight() {
        return light;
    }
    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDiagnostic(BoxAg diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setWater(BoxAg water) {
        this.water = water;
    }

    public void setLight(BoxAg light) {
        this.light = light;
    }
}
