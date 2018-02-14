package com.example.alex.myapplication;

/**
 * Created by Alex on 2/6/2018.
 */

public class DataProviderToCustomAdapter {

    private int ID;
    private String name;
    private int Kcal;
    private int gram;
    private int Protein;
    private int Carbs;
    private int Fats;
    private int Quantity;

    public DataProviderToCustomAdapter(int ID, String name, int kcal, int gram, int protein, int carbs, int fats, int quantity) {
        this.ID = ID;
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
        Quantity = quantity;
    }

    public DataProviderToCustomAdapter(String name, int kcal, int gram, int protein, int carbs, int fats, int quantity) {
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
        Quantity = quantity;
    }

    public DataProviderToCustomAdapter(String name, int kcal, int gram, int protein, int carbs, int fats) {
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
    }

    public DataProviderToCustomAdapter(int ID, String name, int kcal, int gram, int protein, int carbs, int fats) {
        this.ID = ID;
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return Kcal;
    }

    public void setKcal(int kcal) {
        Kcal = kcal;
    }

    public int getGram() {
        return gram;
    }

    public void setGram(int gram) {
        this.gram = gram;
    }

    public int getProtein() {
        return Protein;
    }

    public void setProtein(int protein) {
        Protein = protein;
    }

    public int getCarbs() {
        return Carbs;
    }

    public void setCarbs(int carbs) {
        Carbs = carbs;
    }

    public int getFats() {
        return Fats;
    }

    public void setFats(int fats) {
        Fats = fats;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
