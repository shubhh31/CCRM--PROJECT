package edu.ccrm.domain;

public class Course {

    private final String code; 
    private final String title; 
    private final int credits;
    private Instructor instructor;
    private final String department;

    private Course(Builder builder)
    {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.department = builder.department;
    }
    public String getCode() 
    {
        return code;
    }
    public String getTitle() 
    {
        return title;
    }
    public int getCredits() 
    {
        return credits;
    }
    public Instructor getInstructor() 
    {
        return instructor;
    }
    public String getDepartment() 
    {
        return department;
    }

    public void setInstructor(Instructor instructor) 
    {
        this.instructor = instructor;
    }

    @Override
    public String toString() 
    {
        String instructorName = (instructor != null) ? instructor.getFullName() : "Not Assigned";
        return String.format("Course[Code=%s, Title='%s', Credits=%d, Dept=%s, Instructor=%s]",
                code, title, credits, department, instructorName);
    }
    public static class Builder 
    {
        private final String code;
        private final String title;
        private int credits = 3;
        private Instructor instructor = null;
        private String department = "General";
        public Builder(String code, String title) 
        {
            this.code = code;
            this.title = title;
        }
        public Builder credits(int credits) 
        {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) 
        {
            this.instructor = instructor;
            return this;
        }

        public Builder department(String department) 
        {
            this.department = department;
            return this;
        }
        public Course build() 
        {
            return new Course(this);
        }
    }
}
