package aitu.studentapi.repository;

import aitu.studentapi.domain.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseRepository {
    List<Course> findAll() throws SQLException;
    Course findById(int id) throws SQLException;
    void save(Course c) throws SQLException;
    void delete(int id) throws SQLException;
}
