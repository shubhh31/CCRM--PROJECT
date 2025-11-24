package edu.ccrm.config;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;

public class DataStore 
{

    private static final DataStore instance = new DataStore();
    public final List<Student> students;
    public final List<Course> courses;
    public final List<Instructor> instructors;
    
    private DataStore() 
    {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        instructors = new ArrayList<>();
    }
    
    public static DataStore getInstance() 
    {
        return instance;
    }
}

