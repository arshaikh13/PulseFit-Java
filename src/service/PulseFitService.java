package service;

import model.MealLog;
import model.WorkoutLog;
import java.util.ArrayList;
import java.util.List;

public class PulseFitService {

    private final List<WorkoutLog> workoutLogs;
    private final List<MealLog> mealLogs;

    public PulseFitService() {
        workoutLogs = new ArrayList<>();
        mealLogs = new ArrayList<>();
    }

    public void logWorkout(WorkoutLog workoutLog) {
        workoutLogs.add(workoutLog);
    }

    public void logMeal(MealLog mealLog) {
        mealLogs.add(mealLog);
    }

    public List<WorkoutLog> getWorkoutLogs() {
        return new ArrayList<>(workoutLogs);
    }

    public List<MealLog> getMealLogs() {
        return new ArrayList<>(mealLogs);
    }

    public double getTotalCaloriesBurned() {
        return workoutLogs.stream().mapToDouble(WorkoutLog::getCaloriesBurned).sum();
    }

    public double getTotalCaloriesConsumed() {
        return mealLogs.stream().mapToDouble(MealLog::getCalories).sum();
    }
}
