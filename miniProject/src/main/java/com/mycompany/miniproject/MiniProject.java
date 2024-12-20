/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.miniproject;

/**
 *
 * @author Sohail
 */
import java.io.*;
import java.util.*;

public class MiniProject {

    private static String[] studentNames;
    private static int[] studentGrades;
    private static int studentCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Student Grades ---");
            System.out.println("1. Load Student Data");
            System.out.println("2. Display Student Information");
            System.out.println("3. Find a Student's Grade");
            System.out.println("4. Calculate Class Average");
            System.out.println("5. Edit a Student's Grade");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        loadStudentData("C:\\Users\\Sohail\\Desktop\\java project\\students.txt");
                        break;
                    case 2:
                        displayStudentInformation();
                        break;
                    case 3:
                        findStudentGrade(scanner);
                        break;
                    case 4:
                        calculateClassAverage();
                        break;
                    case 5:
                        editStudentGrade(scanner);
                        break;
                    case 6:
                        isRunning = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        scanner.close();
    }

    private static void loadStudentData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            studentCount = Integer.parseInt(reader.readLine().trim());
            if (studentCount <= 0) {
                System.out.println("Error: The file contains no student data.");
                return;
            }

            studentNames = new String[studentCount];
            studentGrades = new int[studentCount];

            for (int i = 0; i < studentCount; i++) {
                studentNames[i] = reader.readLine().trim();
                studentGrades[i] = Integer.parseInt(reader.readLine().trim());
            }

            System.out.println("Student data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please ensure the file path is correct.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid file format. Please check the data in the file.");
        }
    }

    private static void displayStudentInformation() {
        if (studentNames == null || studentGrades == null) {
            System.out.println("No data available. Please load student data first.");
            return;
        }

        System.out.println("\n--- Student Information ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%s: %d%n", studentNames[i], studentGrades[i]);
        }
    }

    private static void findStudentGrade(Scanner scanner) {
        if (studentNames == null) {
            System.out.println("No data available. Please load student data first.");
            return;
        }

        System.out.print("Enter the student's name: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i].equalsIgnoreCase(name)) {
                System.out.printf("Grade for %s: %d%n", name, studentGrades[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private static void calculateClassAverage() {
        if (studentGrades == null) {
            System.out.println("No data available. Please load student data first.");
            return;
        }

        int total = 0;
        for (int grade : studentGrades) {
            total += grade;
        }

        double average = (double) total / studentCount;
        System.out.printf("Class average: %.2f%n", average);
    }

    private static void editStudentGrade(Scanner scanner) {
        if (studentNames == null) {
            System.out.println("No data available. Please load student data first.");
            return;
        }

        System.out.print("Enter the student's name to edit: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i].equalsIgnoreCase(name)) {
                System.out.printf("Current grade for %s: %d%n", name, studentGrades[i]);
                System.out.print("Enter the new grade: ");
                try {
                    studentGrades[i] = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Grade updated successfully.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid grade input. Please enter a valid number.");
                    scanner.nextLine(); // Clear invalid input
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}
