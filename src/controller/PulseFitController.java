package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MealLog;
import model.WorkoutLog;
import util.FileUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PulseFitController {
    // Users Tab
    @FXML private ListView<String> usersListView;
    @FXML private TextField userIdField, userNameField, userAgeField, userWeightField, userHeightField, userFitnessField;
    @FXML private Button addUserButton, deleteUserButton, deleteMealButton;

    // Workout Tab
    @FXML private ListView<String> workoutLogsListView;
    @FXML private ComboBox<String> workoutTypeDropdown;
    @FXML private TextField workoutDurationField;
    @FXML private Button logWorkoutButton, deleteWorkoutButton;

    // Meal Tab
    @FXML private ListView<String> mealLogsListView;
    @FXML private TextField foodItem, calories, protein, carbs, fats;
    @FXML private Button logMealBtn;

    // Trainer Tab
    @FXML private ListView<String> trainerListView;
    @FXML private TextField trainerIdField, trainerNameField, trainerAgeField, trainerWeightField, trainerHeightField, trainerFitnessField;
    @FXML private Button addTrainerButton, deleteTrainerButton;

    // Plans Tab
    @FXML private ComboBox<String> userSelectComboBox;
    @FXML private ListView<String> availablePlansListView, assignedPlansListView;
    @FXML private TextField newPlanField;
    @FXML private Button deletePlanButton, createPlanButton;

    private final ObservableList<String> users = FXCollections.observableArrayList();
    private final ObservableList<String> trainers = FXCollections.observableArrayList();
    private final ObservableList<String> workoutLogs = FXCollections.observableArrayList();
    private final ObservableList<String> mealLogs = FXCollections.observableArrayList();
    private final ObservableList<String> plans = FXCollections.observableArrayList();

    private static final String USERS_FILE = "resources/users.txt";
    private static final String TRAINERS_FILE = "resources/trainers.txt";
    private static final String WORKOUT_LOG_FILE = "resources/workoutlogs.txt";
    private static final String MEAL_LOG_FILE = "resources/mealogs.txt";
    private static final String PLANS_FILE = "resources/workoutplans.txt";
    private static final String USER_PLAN_FILE = "resources/user_plans.txt";

    @FXML
    public void initialize() {
        workoutTypeDropdown.setItems(FXCollections.observableArrayList(
            "Running", "Cycling", "Swimming", "Yoga", "Weight Lifting", "Walking",
            "Push Ups", "Squats", "Bench Press", "Deadlift", "Plank", "Lunges",
            "Overhead Press", "Burpees", "Pull Ups"
        ));

        loadUsers();
        loadTrainers();
        loadWorkoutLogs();
        loadMealLogs();
        loadPlans();
        populateUserComboBox();

        addUserButton.setOnAction(e -> addUser());
        deleteUserButton.setOnAction(e -> deleteUser());
        addTrainerButton.setOnAction(e -> addTrainer());
        deleteTrainerButton.setOnAction(e -> deleteTrainer());
        logWorkoutButton.setOnAction(e -> handleLogWorkout());
        deleteWorkoutButton.setOnAction(e -> deleteWorkoutLog());
        logMealBtn.setOnAction(e -> handleLogMeal());
        deleteMealButton.setOnAction(e -> deleteMealLog());
        createPlanButton.setOnAction(e -> createNewPlan());
        deletePlanButton.setOnAction(e -> deletePlan());
        userSelectComboBox.setOnAction(e -> loadUserPlans());
    }

    private void loadUsers() {
        users.clear();
        for (String line : FileUtil.readLines(USERS_FILE)) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                String formatted = String.format("%s - %s, Age: %s, Weight: %slbs, Height: %sft, Level: %s, Role: %s",
                        parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                users.add(formatted);
            }
        }
        usersListView.setItems(users);
    }

    private void addUser() {
        try {
            String user = formatUserLine(userIdField.getText(), userNameField.getText(), userAgeField.getText(),
                    userWeightField.getText(), userHeightField.getText(), userFitnessField.getText());
            if (user != null) {
                FileUtil.appendLine(USERS_FILE, user + ",Member");
                loadUsers();
                populateUserComboBox();
                clearUserFields();
            } else {
                showAlert("User input is invalid.");
            }
        } catch (Exception ex) {
            showAlert("Error adding user: " + ex.getMessage());
        }
    }

    private void populateUserComboBox() {
        ObservableList<String> userIds = FXCollections.observableArrayList();
        for (String line : FileUtil.readLines(USERS_FILE)) {
            String[] parts = line.split(",");
            if (parts.length > 0) userIds.add(parts[0]);
        }
        userSelectComboBox.setItems(userIds);
    }

    private void loadUserPlans() {
        String selectedUser = userSelectComboBox.getValue();
        ObservableList<String> userPlans = FXCollections.observableArrayList();
        for (String line : FileUtil.readLines(USER_PLAN_FILE)) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equals(selectedUser)) {
                userPlans.add(parts[1]);
            }
        }
        assignedPlansListView.setItems(userPlans);
    }

    private void createNewPlan() {
        String planName = newPlanField.getText();
        if (!planName.isEmpty()) {
            FileUtil.appendLine(PLANS_FILE, planName);
            loadPlans();
            newPlanField.clear();
        }
    }

    private void deletePlan() {
        String selected = availablePlansListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            plans.remove(selected);
            FileUtil.writeLines(PLANS_FILE, plans);
        }
    }

    private void loadPlans() {
        plans.setAll(FileUtil.readLines(PLANS_FILE));
        availablePlansListView.setItems(plans);
    }

    private void deleteUser() {
        String selected = usersListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            users.remove(selected);
            List<String> rawUsers = new ArrayList<>();
            for (String formatted : users) {
                rawUsers.add(parseDisplayLineToCSV(formatted));
            }
            FileUtil.writeLines(USERS_FILE, rawUsers);
        }
    }

    private void clearUserFields() {
        userIdField.clear(); userNameField.clear(); userAgeField.clear();
        userWeightField.clear(); userHeightField.clear(); userFitnessField.clear();
    }

    private void loadTrainers() {
        trainers.clear();
        for (String line : FileUtil.readLines(TRAINERS_FILE)) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String display = String.format("ID: %s | Name: %s | Age: %s | Weight: %s lbs | Height: %s ft | Fitness: %s",
                        parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                trainers.add(display);
            }
        }
        trainerListView.setItems(trainers);
    }

    private void addTrainer() {
        try {
            String trainer = formatUserLine(trainerIdField.getText(), trainerNameField.getText(), trainerAgeField.getText(),
                    trainerWeightField.getText(), trainerHeightField.getText(), trainerFitnessField.getText());
            if (trainer != null) {
                FileUtil.appendLine(TRAINERS_FILE, trainer);
                loadTrainers();
                clearTrainerFields();
            } else {
                showAlert("Trainer input is invalid.");
            }
        } catch (Exception ex) {
            showAlert("Error adding trainer: " + ex.getMessage());
        }
    }

    private void deleteTrainer() {
        String selected = trainerListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            trainers.remove(selected);
            List<String> rawTrainers = new ArrayList<>();
            for (String formatted : trainers) {
                rawTrainers.add(parseDisplayLineToCSV(formatted));
            }
            FileUtil.writeLines(TRAINERS_FILE, rawTrainers);
        }
    }

    private void clearTrainerFields() {
        trainerIdField.clear(); trainerNameField.clear(); trainerAgeField.clear();
        trainerWeightField.clear(); trainerHeightField.clear(); trainerFitnessField.clear();
    }

    private String formatUserLine(String id, String name, String age, String weight, String height, String fitness) {
        if (id.isEmpty() || name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || fitness.isEmpty()) {
            return null;
        }
        try {
            int parsedAge = Integer.parseInt(age);
            double parsedWeight = Double.parseDouble(weight);
            double parsedHeight = Double.parseDouble(height);
            return String.join(",", id, name, String.valueOf(parsedAge),
                    String.valueOf(parsedWeight), String.valueOf(parsedHeight), fitness);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void loadWorkoutLogs() {
        workoutLogs.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (WorkoutLog log : FileUtil.loadWorkoutLogs(WORKOUT_LOG_FILE)) {
            String display = String.format("%s (%s) - %.1f mins - %.1f kcal",
                    log.getExerciseName(), sdf.format(log.getDate()), log.getDuration(), log.getCaloriesBurned());
            workoutLogs.add(display);
        }
        workoutLogsListView.setItems(workoutLogs);
    }

    private void handleLogWorkout() {
        try {
            String type = workoutTypeDropdown.getValue();
            String durationText = workoutDurationField.getText();
            if (type == null || durationText.isEmpty()) {
                showAlert("Please select a workout type and enter a duration.");
                return;
            }
            int duration = Integer.parseInt(durationText);
            WorkoutLog log = new WorkoutLog(type, 0, 0, 0.0, duration, new Date());
            FileUtil.appendWorkoutLog(log, WORKOUT_LOG_FILE);
            loadWorkoutLogs();
        } catch (NumberFormatException ex) {
            showAlert("Invalid input for duration.");
        }
    }

    private void deleteWorkoutLog() {
        String selected = workoutLogsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            workoutLogs.remove(selected);
            FileUtil.writeLines(WORKOUT_LOG_FILE, workoutLogs);
        }
    }

    private void loadMealLogs() {
        mealLogs.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (MealLog log : FileUtil.loadMealLogs(MEAL_LOG_FILE)) {
            String display = String.format("%s (%s) - %d kcal",
                    log.getFoodItem(), sdf.format(log.getDate()), log.getCalories());
            mealLogs.add(display);
        }
        mealLogsListView.setItems(mealLogs);
    }

    private void deleteMealLog() {
        String selected = mealLogsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            mealLogs.remove(selected);
            FileUtil.writeLines(MEAL_LOG_FILE, mealLogs);
        }
    }

    private void handleLogMeal() {
        try {
            String name = foodItem.getText();
            String calText = calories.getText();
            String proteinText = protein.getText();
            String carbsText = carbs.getText();
            String fatsText = fats.getText();

            if (name.isEmpty() || calText.isEmpty()) {
                showAlert("Please fill out the food item and calories.");
                return;
            }

            int cal = Integer.parseInt(calText);
            double prot = proteinText.isEmpty() ? 0 : Double.parseDouble(proteinText);
            double crb = carbsText.isEmpty() ? 0 : Double.parseDouble(carbsText);
            double ft = fatsText.isEmpty() ? 0 : Double.parseDouble(fatsText);

            MealLog log = new MealLog(name, cal, prot, crb, ft, new Date());
            FileUtil.appendMealLog(log, MEAL_LOG_FILE);
            loadMealLogs();
        } catch (NumberFormatException ex) {
            showAlert("Invalid number input in meal fields.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private String parseDisplayLineToCSV(String displayLine) {
        try {
            String id = displayLine.substring(0, displayLine.indexOf(" - "));
            String name = displayLine.substring(displayLine.indexOf(" - ") + 3, displayLine.indexOf(", Age:"));
            String age = displayLine.split("Age: ")[1].split(",")[0].trim();
            String weight = displayLine.split("Weight: ")[1].split("lbs")[0].trim();
            String height = displayLine.split("Height: ")[1].split("ft")[0].trim();
            String level = displayLine.split("Level: ")[1].split(",")[0].trim();
            String role = displayLine.split("Role: ")[1].trim();

            return String.join(",", id, name, age, weight, height, level, role);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
