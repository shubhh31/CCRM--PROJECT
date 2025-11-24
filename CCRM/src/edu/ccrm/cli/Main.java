package edu.ccrm.cli;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.enums.Grade;
import edu.ccrm.exception.CourseNotFoundException;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.io.FileService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

//main console program 
public class Main {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final FileService fileService = new FileService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final AtomicLong studentIdGenerator = new AtomicLong(1000);

    public static void main(String[] args) {

        System.out.println("\n-------- Campus Course & Records Manager (CCRM) --------");
        
        try {
            fileService.importData();
        } catch (IOException e) {
            System.err.println("Could not load initial data: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            printMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageCourses();
                        break;
                    case 3:
                        manageEnrollments();
                        break;
                    case 4:
                        manageData();
                        break;
                    case 5:
                        printPlatformNote();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments & Grades");
        System.out.println("4. Data Utilities (Save/Load/Backup)");
        System.out.println("5. View Platform Info");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Submenu 
    private static void manageStudents() {
        System.out.println("\n-- Manage Students --");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.println("3. View Student Profile & Transcript");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter full name: ");
                String name = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter registration number: ");
                String regNo = scanner.nextLine();
                studentService.addStudent(new Student(studentIdGenerator.incrementAndGet(), name, email, regNo));
                System.out.println("Student added successfully!");
                break;
            case 2:
                List<Student> students = studentService.getAllStudents();
                if(students.isEmpty()) {
                    System.out.println("No students in the system.");
                } else {
                    students.forEach(System.out::println);
                }
                break;
            case 3:
                System.out.print("Enter student registration number: ");
                String reg = scanner.nextLine();
                Optional<Student> studentOpt = studentService.findStudentByRegNo(reg);
                studentOpt.ifPresentOrElse(
                    student -> {
                        System.out.println(student.getProfileDetails());
                        studentService.printTranscript(student);
                    },
                    () -> System.out.println("Student not found.")
                );
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageCourses() {
         System.out.println("\n-- Manage Courses --");
         System.out.println("1. Add New Course");
         System.out.println("2. List All Courses");
         System.out.print("Choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine();

         switch(choice) {
            case 1:
                System.out.print("Enter course code (e.g., CS101): ");
                String code = scanner.nextLine();
                System.out.print("Enter course title: ");
                String title = scanner.nextLine();
                System.out.print("Enter credits: ");
                int credits = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter department: ");
                String dept = scanner.nextLine();

                Course newCourse = new Course.Builder(code, title) //builder pattern 
                    .credits(credits)
                    .department(dept)
                    .build();
                courseService.addCourse(newCourse);
                System.out.println("Course added!");
                break;
            case 2:
                courseService.getAllCourses().forEach(System.out::println);
                break;
            default:
                 System.out.println("Invalid choice.");
         }
    }

    private static void manageEnrollments() {
        System.out.println("\n-- Manage Enrollments --");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. Assign Grade to Student");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine();
        Optional<Student> studentOpt = studentService.findStudentByRegNo(regNo);

        if (studentOpt.isEmpty()) {
            System.out.println("Student not found.");
            return;
        }
        Student student = studentOpt.get();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Optional<Course> courseOpt = courseService.findCourseByCode(courseCode);

        if (courseOpt.isEmpty()) {
            System.out.println("Course not found.");
            return;
        }
        Course course = courseOpt.get();

        try {
            if (choice == 1) {
                enrollmentService.enrollStudent(student, course);
                System.out.println("Enrollment successful!");
            } else if (choice == 2) {
                System.out.print("Enter Grade (S,A,B,C,D,F): ");
                String gradeStr = scanner.nextLine().toUpperCase();
                Grade grade = Grade.valueOf(gradeStr);
                enrollmentService.assignGrade(student, course, grade);
                System.out.println("Grade assigned successfully!");
            }
        } catch (DuplicateEnrollmentException | CourseNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid grade entered.");
        }
    }
    
    private static void manageData() {
        System.out.println("\n--- Data Utilities ---");
        System.out.println("1 -> Save All Data [Export]");
        System.out.println("2 -> Create Backup");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        try {
            if (choice == 1) {
                fileService.exportData();
            } else if (choice == 2) {
                fileService.runBackup();
            }
        } catch (IOException e) {
            System.err.println("A file operation failed: " + e.getMessage());
        }
    }
    
    private static void printPlatformNote() {
        System.out.println("\n--- Java Platform Quick Info ---");
        System.out.println("This application is built using Java SE (Standard Edition).");
        System.out.println("Java SE vs ME vs EE:");
        System.out.println(" - Java SE (Standard Edition): For general-purpose desktop and server applications. The core Java platform.");
        System.out.println(" - Java ME (Micro Edition): For small, resource-constrained devices like mobile phones and embedded systems.");
        System.out.println(" - Java EE (Enterprise Edition): Built on top of SE, it provides a larger framework for building large-scale, multi-tiered, and reliable enterprise applications (like web services).");
        System.out.println("Made BY -  shubh acharya 24BCE11494");
    }
}
