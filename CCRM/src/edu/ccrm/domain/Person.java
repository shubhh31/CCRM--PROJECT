package edu.ccrm.domain;

public abstract class Person 
{
    private long id;
    private String fullName;
    private String email;

    protected Person(long id, String fullName, String email) 
    {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
    public abstract String getProfileDetails();
    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getFullName() 
    {
        return fullName;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
}

