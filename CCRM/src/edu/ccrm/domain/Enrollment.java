package edu.ccrm.domain;
import edu.ccrm.domain.enums.Grade;
import java.time.LocalDate;

public class Enrollment
{
    private final Course course;
    private final LocalDate enrollmentDate;
    private Grade grade;

    public Enrollment(Course course) 
    {
        this.course = course;
        this.enrollmentDate = LocalDate.now();
        this.grade = Grade.NA;
    }

    public Course getCourse() 
    {
        return course;
    }

    public LocalDate getEnrollmentDate() 
    {
        return enrollmentDate;
    }

    public Grade getGrade() 
    {
        return grade;
    }

    public void setGrade(Grade grade) 
    {
        this.grade = grade;
    }

    @Override
    public String toString() 
    {
        return String.format("Enrollment[Course=%s, Grade=%s]", course.getCode(), grade.getLetterGrade());
    }
}
