package model;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<WorkoutLog> workoutLogs;
    private List<MealLog> mealLogs;
    private Trainer assignedTrainer;

    public Member(String userId, String name, int age, double weight, double height, String fitnessLevel) {
        super(userId, name, age, weight, height, fitnessLevel);
        this.workoutLogs = new ArrayList<>();
        this.mealLogs = new ArrayList<>();
    }

    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogs;
    }

    public List<MealLog> getMealLogs() {
        return mealLogs;
    }

    public Trainer getAssignedTrainer() {
        return assignedTrainer;
    }

    public void setAssignedTrainer(Trainer trainer) {
        this.assignedTrainer = trainer;
    }

    public void setGoals(String goal) {
        System.out.println("Setting goal: " + goal);
    }

    public List<WorkoutPlan> viewAssignedWorkout() {
        if (assignedTrainer != null) {
            return assignedTrainer.getAssignedPlans();
        }
        return new ArrayList<>();
    }

    @Override
    public void viewProgress() {
        System.out.println("Viewing progress for member: " + getName());
    }

    @Override
    public void logWorkout() {
        System.out.println("Workout logged for member: " + getName());
    }

    @Override
    public void logMeal() {
        System.out.println("Meal logged for member: " + getName());
    }
} 