package Garden;

import ManyToOne.City;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Garden {
    private int id;
    private String status;
    private int sort;
    private String user_created;
    private String user_updated;
    private String owner;
    private City city;
    private ArrayList<BoxAg> boxAGs;
    private ArrayList<Plant> plants;


    // Serializing and deserializing
    public String serialization(){
        String serialized = new Gson().toJson(this);
        return serialized;
    }

    public Garden deserialization(String jsonInput){
        Garden garden = new Gson().fromJson(jsonInput,Garden.class);
        return garden;
    }
    // Some useful methods

    public void addBoxAg(BoxAg boxAg){
        boxAGs.add(boxAg);
    }

    public void addPlant(Plant plant){
        plants.add(plant);
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

    public String getOwner() {
        return owner;
    }

    public City getCity() {
        return city;
    }

    public ArrayList<BoxAg> getBoxAgArrayList() {
        return boxAGs;
    }

    public ArrayList<Plant> getPlantArrayList() {
        return plants;
    }

    // Setters

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setBoxAgArrayList(ArrayList<BoxAg> boxAgArrayList) {
        this.boxAGs = boxAgArrayList;
    }

    public void setPlantArrayList(ArrayList<Plant> plantArrayList) {
        this.plants = plantArrayList;
    }


}
