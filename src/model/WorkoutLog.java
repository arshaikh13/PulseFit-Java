package model;

import java.util.Date;

public class WorkoutLog {
	
    private String exerciseName;
    private int reps;
    private int sets;
    private double weightLifted;
    private double duration;
    private double caloriesBurned;
    private Date date;

    public WorkoutLog(String exerciseName, int reps, int sets, double weightLifted, double duration, Date date) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.weightLifted = weightLifted;
        this.duration = duration;
        this.date = date;
        this.caloriesBurned = calculateCaloriesBurned();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public double getWeightLifted() {
        return weightLifted;
    }

    public double getDuration() {
        return duration;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public Date getDate() {
        return date;
    }

    public double calculateCaloriesBurned() {
        return (reps * sets * weightLifted * 0.1) + (duration * 5);
    }

    @Override
    public String toString() {
        return exerciseName + " - " + duration + " mins, " + caloriesBurned + " kcal burned";
    }
} 
