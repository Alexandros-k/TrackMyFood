package com.example.alex.myapplication;

/**
 * Created by Alex on 2/3/2018.
 */

public class Food {
    private int ID;
    private String name;
    private int Kcal;
    private int gram;
    private int Protein;
    private int Carbs;
    private int Fats;



    public Food(int id,String name, int kcal, int gram, int protein, int carbs, int fats) {
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
        ID=id;
    }

    public Food(String name, int kcal, int gram, int protein, int carbs, int fats) {
        this.name = name;
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
    }

    public Food(int kcal, int gram, int protein, int carbs, int fats) {
        Kcal = kcal;
        this.gram = gram;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
    }

    public Food() {

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


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Food{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", Kcal=" + Kcal +
                ", gram=" + gram +
                ", Protein=" + Protein +
                ", Carbs=" + Carbs +
                ", Fats=" + Fats +
                '}';
    }
}
