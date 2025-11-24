package edu.ccrm.service;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.enums.Grade;
import edu.ccrm.exception.CourseNotFoundException;
import edu.ccrm.exception.DuplicateEnrollmentException;
import java.util.Optional;

public class EnrollmentService {

    public void enrollStudent(Student student, Course course) throws DuplicateEnrollmentException {
    
        boolean alreadyEnrolled = student.getEnrolledCourses().stream()
                .anyMatch(e -> e.getCourse().getCode().equalsIgnoreCase(course.getCode()));

        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student " + student.getRegNo() + " is already enrolled in course " + course.getCode());
        }

        Enrollment newEnrollment = new Enrollment(course);
        student.addEnrollment(newEnrollment);
    }

    public void assignGrade(Student student, Course course, Grade grade) throws CourseNotFoundException {
        Optional<Enrollment> enrollmentOpt = student.getEnrolledCourses().stream()
                .filter(e -> e.getCourse().getCode().equalsIgnoreCase(course.getCode()))
                .findFirst();

        if (enrollmentOpt.isPresent()) {
            enrollmentOpt.get().setGrade(grade);
        } else {
            throw new CourseNotFoundException("Cannot assign grade. Student " + student.getRegNo() + " is not enrolled in course " + course.getCode());
        }
    }
}
