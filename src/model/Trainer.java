package model;

import java.util.ArrayList;
import java.util.List;

public class Trainer extends User {
	
    private List<Member> clients;
    private List<WorkoutPlan> assignedPlans;

    public Trainer(String userId, String name, int age, double weight, double height, String fitnessLevel) {
        super(userId, name, age, weight, height, fitnessLevel);
        this.clients = new ArrayList<>();
        this.assignedPlans = new ArrayList<>();
    }

    public List<Member> getClients() {
        return clients;
    }

    public List<WorkoutPlan> getAssignedPlans() {
        return assignedPlans;
    }

    public void assignWorkoutPlan(Member member, WorkoutPlan plan) {
        member.setAssignedTrainer(this);
        assignedPlans.add(plan);
        if (!clients.contains(member)) {
            clients.add(member);
        }
        System.out.println("Assigned plan to: " + member.getName());
    }

    public void viewClientProgress(Member member) {
        System.out.println("Progress for " + member.getName() + ":");
        member.viewProgress();
    }

    @Override
    public void viewProgress() {
        System.out.println("Trainers do not have progress to view.");
    }

    @Override
    public void logWorkout() {
        System.out.println("Trainers do not log workouts.");
    }

    @Override
    public void logMeal() {
        System.out.println("Trainers do not log meals.");
    }
} 
