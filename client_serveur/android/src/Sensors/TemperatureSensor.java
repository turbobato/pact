package Sensors;

import com.google.gson.Gson;

public class TemperatureSensor {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String name;
    private float value;
    private int state;

    // Serializing and deserializing

    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public TemperatureSensor deserialization(String jsonInput){
        TemperatureSensor temperatureSensor = new Gson().fromJson(jsonInput,TemperatureSensor.class);
        return temperatureSensor;
    }

    // Getters and Setters
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getState() {
        return state;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setState(int state) {
        this.state = state;
    }
}
