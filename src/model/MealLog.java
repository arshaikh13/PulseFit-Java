package model;

import java.util.Date;

public class MealLog {
	
    private String foodItem;
    private int calories;
    private double protein;
    private double carbs;
    private double fats;
    private Date date;

    public MealLog(String foodItem, int calories, double protein, double carbs, double fats, Date date) {
        this.foodItem = foodItem;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.date = date;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public int getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public Date getDate() {
        return date;
    }

    public void setGoals(String goal) {
        System.out.println("Meal goal set: " + goal);
    }

    public void viewAssignedWorkout() {
        System.out.println("Viewing assigned workouts not available from MealLog");
    }

    @Override
    public String toString() {
        return foodItem + ": " + calories + " kcal, Protein: " + protein + "g, Carbs: " + carbs + "g, Fats: " + fats + "g";
    }
} 
