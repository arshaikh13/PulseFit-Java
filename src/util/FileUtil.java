package util;

import model.WorkoutLog;
import model.MealLog;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;

public class FileUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // 🏋️ WORKOUT LOG METHODS
    public static List<WorkoutLog> loadWorkoutLogs(String filename) {
        List<WorkoutLog> logs = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return logs;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Date date = formatter.parse(parts[5]);
                    logs.add(new WorkoutLog(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        date
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }

    public static void appendWorkoutLog(WorkoutLog log, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.join(",",
                log.getExerciseName(),
                String.valueOf(log.getReps()),
                String.valueOf(log.getSets()),
                String.valueOf(log.getWeightLifted()),
                String.valueOf(log.getDuration()),
                formatter.format(log.getDate())
            ));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🍽️ MEAL LOG METHODS
    public static List<MealLog> loadMealLogs(String filename) {
        List<MealLog> logs = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return logs;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Date date = formatter.parse(parts[5]);
                    logs.add(new MealLog(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        date
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }

    public static void appendMealLog(MealLog log, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.join(",",
                log.getFoodItem(),
                String.valueOf(log.getCalories()),
                String.valueOf(log.getProtein()),
                String.valueOf(log.getCarbs()),
                String.valueOf(log.getFats()),
                formatter.format(log.getDate())
            ));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 📋 GENERIC TEXT FILE READER
    public static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    // 🧾 TEXT FILE WRITERS
    public static void writeLines(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendLine(String filename, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void overwriteFile(ObservableList<String> data, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


