package ManyToOne;

import com.google.gson.Gson;

public class City {
    private int id;
    private String status;
    private int sort;
    private String city;

    // Serializing and deserializing
    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public City deserialization(String jsonInput){
        City city = new Gson().fromJson(jsonInput,City.class);
        return city;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getSort() {
        return sort;
    }

    public String getCity() {
        return city;
    }

    //

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }
}
