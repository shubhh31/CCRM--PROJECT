package edu.ccrm.domain;
import edu.ccrm.domain.enums.StudentStatus;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person 
{

    private String regNo;
    private StudentStatus status;
    private final List<Enrollment> enrolledCourses;

    public Student(long id, String fullName, String email, String regNo) 
    {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = StudentStatus.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public String getProfileDetails() 
    {
        return String.format("--- STUDENT PROFILE ---\nID: %d\nName: %s\nEmail: %s\nReg No: %s\nStatus: %s\n",
                getId(), getFullName(), getEmail(), this.regNo, this.status);
    }

    public void addEnrollment(Enrollment enrollment) 
    {
        this.enrolledCourses.add(enrollment);
    }
    public String getRegNo() 
    {
        return regNo;
    }
    public void setRegNo(String regNo) 
    {
        this.regNo = regNo;
    }

    public StudentStatus getStatus() 
    {
        return status;
    }

    public void setStatus(StudentStatus status)
    {
        this.status = status;
    }

    public List<Enrollment> getEnrolledCourses() 
    {
        return enrolledCourses;
    }

    @Override
    public String toString() 
    {
        return String.format("Student[ID=%d, Name=%s, RegNo=%s]", getId(), getFullName(), regNo);
    }
}
