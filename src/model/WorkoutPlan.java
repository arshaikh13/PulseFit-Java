package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan {
    private String planId;
    private String planName;
    private List<WorkoutLog> exercises;
    private Trainer assignedBy;

    public WorkoutPlan(String planId, String planName, Trainer assignedBy) {
        this.planId = planId;
        this.planName = planName;
        this.assignedBy = assignedBy;
        this.exercises = new ArrayList<>();
    }

    public String getPlanId() {
        return planId;
    }

    public String getPlanName() {
        return planName;
    }

    public List<WorkoutLog> getExercises() {
        return exercises;
    }

    public Trainer getAssignedBy() {
        return assignedBy;
    }

    public void addExercise(WorkoutLog log) {
        exercises.add(log);
    }

    public String viewPlanDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Workout Plan: ").append(planName).append("\n");
        for (WorkoutLog log : exercises) {
            sb.append(log.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return planName + " (ID: " + planId + ")";
    }
} 
