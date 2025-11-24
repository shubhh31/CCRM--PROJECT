package edu.ccrm.domain.enums;

public enum Grade 
{
    S("S", 10.0),
    A("A", 9.0),
    B("B", 8.0),
    C("C", 7.0),
    D("D", 6.0),
    F("F", 0.0),
    NA("Not Graded", 0.0);

    private final String letterGrade;
    private final double points;

    Grade(String letterGrade, double points) 
    {
        this.letterGrade = letterGrade;
        this.points = points;
    }

    public String getLetterGrade() 
    {
        return letterGrade;
    }

    public double getPoints() 
    {
        return points;
    }
}

