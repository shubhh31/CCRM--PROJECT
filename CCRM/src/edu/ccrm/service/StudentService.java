package edu.ccrm.service;
import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.enums.Grade;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentService implements Searchable<Student> {

    private final DataStore dataStore = DataStore.getInstance();

    public void addStudent(Student student) {
        boolean exists = dataStore.students.stream()
                .anyMatch(s -> s.getRegNo().equalsIgnoreCase(student.getRegNo()));
        if (!exists) {
            dataStore.students.add(student);
        } else {
            System.err.println("Error: Student with RegNo " + student.getRegNo() + " already exists.");
        }
    }

    public List<Student> getAllStudents() {
        return dataStore.students;
    }

    public Optional<Student> findStudentByRegNo(String regNo) {
        return dataStore.students.stream()
                .filter(s -> s.getRegNo().equalsIgnoreCase(regNo))
                .findFirst();
    }

    public void printTranscript(Student student) {
        System.out.println("\n--- TRANSCRIPT for " + student.getFullName() + " ---");
        System.out.println("Registration No: " + student.getRegNo());
        System.out.println("-------------------------------------------");

        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        student.getEnrolledCourses().forEach(e -> {
            System.out.printf("Course: %s (%s) | Grade: %s\n",
                    e.getCourse().getTitle(), e.getCourse().getCode(), e.getGrade().getLetterGrade());
        });

        double totalPoints = student.getEnrolledCourses().stream()
            .filter(e -> e.getGrade() != Grade.NA)
            .mapToDouble(e -> e.getGrade().getPoints() * e.getCourse().getCredits())
            .sum();

        int totalCredits = student.getEnrolledCourses().stream()
            .filter(e -> e.getGrade() != Grade.NA)
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();

        if (totalCredits > 0) {
            double gpa = totalPoints / totalCredits;
            System.out.printf("\nCumulative GPA: %.2f\n", gpa);
        } else {
            System.out.println("\nCumulative GPA: N/A (no graded courses)");
        }
        System.out.println("-------------------------------------------");
    }
    @Override
    public List<Student> searchByQuery(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return dataStore.students.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(lowerCaseQuery) ||
                             s.getRegNo().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
}

