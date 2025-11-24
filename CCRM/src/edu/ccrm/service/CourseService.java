package edu.ccrm.service;
import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course> {

    private final DataStore dataStore = DataStore.getInstance();

    public void addCourse(Course course) {
        dataStore.courses.add(course);
    }

    public List<Course> getAllCourses() {
        return dataStore.courses;
    }

    public Optional<Course> findCourseByCode(String code) {
        return dataStore.courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    @Override
    public List<Course> searchByQuery(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return dataStore.courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                             c.getCode().toLowerCase().contains(lowerCaseQuery) ||
                             c.getDepartment().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
}
