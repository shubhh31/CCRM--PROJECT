# CCRM: Campus Course & Records Manager - USAGE Guide

This document provides a quick guide to using the Campus Course & Records Manager (CCRM) command-line application.

## 🚀 Getting Started

1.  *Run the application*: Navigate to the project's root directory and run the main class.
    
    bash
    java -jar ccm.jar
    
    (Or, if running from source in your IDE, execute Main.java)

2.  *Main Menu*: Upon launch, you'll be presented with a menu of options.
    
    
    ------------------------------------------------------
    |        Welcome to CCRM - Main Menu         |
    ------------------------------------------------------
    1. Manage Students
    2. Manage Courses
    3. Manage Enrollment & Grades
    4. Import/Export Data
    5. Backup Data & Show Backup Size
    6. Reports
    7. Exit
    ------------------------------------------------------
    Enter your choice:
    

## 📂 Data Operations

### 1. Importing Data

To import initial data, you must place the CSV files in the test-data/ folder. The application will prompt you to import from these files.

*Example command flow*:
* Choose option 4. Import/Export Data.
* Select Import Students from CSV or Import Courses from CSV.
* The application will read data from test-data/students.csv or test-data/courses.csv.

### 2. Exporting Data

You can export current application data to a data/ folder in the project root. This creates CSV-like files for students, courses, and enrollments.

*Example command flow*:
* Choose option 4. Import/Export Data.
* Select Export All Data.
* The exported files will be saved in a new folder, e.g., data/students.csv, data/courses.csv, etc.

### 3. Backing Up Data

The backup command copies all exported data to a timestamped folder inside a backup/ directory. It also recursively calculates the total size of the backup folder.

*Example command flow*:
* First, export data (see above).
* Choose option 5. Backup Data & Show Backup Size.
* A new folder like backup/2025-09-25_14-30-00/ will be created, containing the exported files.
* The total size of the folder will be printed to the console.

## 📝 Test Data

The test-data/ folder should be located in the root directory of your project, alongside src/ and other project files. It should contain simple, plain-text CSV files for importing.

### students.csv

This file contains sample student data. The format is regNo,fullName,email,status.

```csv
S001,John Doe,johndoe@email.com,ACTIVE
S002,Jane Smith,janesmith@email.com,ACTIVE
S003,Peter Jones,peterjones@email.com,INACTIVE