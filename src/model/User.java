package model;

public abstract class User {
	
    protected String userId;
    protected String name;
    protected int age;
    protected double weight;
    protected double height;
    protected String fitnessLevel;

    public User(String userId, String name, int age, double weight, double height, String fitnessLevel) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessLevel = fitnessLevel;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public abstract void viewProgress();

    public abstract void logWorkout();

    public abstract void logMeal();
}
