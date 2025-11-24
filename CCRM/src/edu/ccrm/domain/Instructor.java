package edu.ccrm.domain;

public class Instructor extends Person {

    private String department;

    public Instructor(long id, String fullName, String email, String department) 
    {
        super(id, fullName, email);
        this.department = department;
    }

    @Override
    public String getProfileDetails() 
    {
        return String.format("--- INSTRUCTOR PROFILE ---\nID: %d\nName: %s\nEmail: %s\nDepartment: %s\n",
                getId(), getFullName(), getEmail(), this.department);
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }
}
