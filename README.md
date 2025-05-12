# PulseFit – Fitness Tracking JavaFX App

**PulseFit** is a desktop application built using JavaFX to help users log workouts, meals, and track their fitness progress. It features separate roles for members and trainers, enabling a structured, goal-oriented approach to health and fitness tracking.

---

## 🧠 Purpose

PulseFit aims to solve the lack of structured fitness tracking by offering a simple, intuitive platform to:
- Log workouts and meals
- Track calories and macros
- View fitness progress over time
- Allow trainers to assign workout plans

---

## 👤 Users

- **Members**: Log workouts, meals, and monitor progress.
- **Trainers**: Assign custom workout plans to members.

---

## 🏗 Architecture

PulseFit follows the **Model-View-Controller (MVC)** pattern:

- **Model** (`model` package):  
  - `User` (abstract) → `Member`, `Trainer`  
  - `WorkoutLog`, `MealLog`, `WorkoutPlan`  
- **View** (`resources/*.fxml`):  
  - JavaFX UI defined in FXML + CSS  
- **Controller** (`controller` package):  
  - Handles form events, screen navigation  
- **Service** (`service` package):  
  - Core business logic, CRUD functions  
- **Util** (`util` package):  
  - Helper classes (file saving, date formatting)

---

## 🛠 Technology Stack

- Java 8+
- JavaFX (SDK v8)
- SceneBuilder
- Eclipse or IntelliJ
- Data stored in local `.txt` files

---

## 🚀 Features

- Role-based access (Member/Trainer)
- Add/Delete Workout Logs, Meal Logs, Users
- Assign and manage Workout Plans
- Track calories, sets, reps, macros
- Clean JavaFX GUI
- Persistent storage via local files

---

## 📦 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/pulsefit.git
