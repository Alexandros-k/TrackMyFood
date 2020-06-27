package com.example.alex.myapplication;

/**
 * Created by Alex on 3/16/2018.
 */

public class DailyGoal {

    int kcal;
    int protein;
    int carbs;
    int fats;

    public DailyGoal(int kcal, int protein, int carbs, int fats) {
        this.kcal = kcal;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }
}
