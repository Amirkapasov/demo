package aitu.studentapi.repository;

import aitu.studentapi.domain.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    List<Student> findAll() throws SQLException;
    Student findById(int id) throws SQLException;
    void save(Student s) throws SQLException;
    void update(int id, Student s) throws SQLException;
    void delete(int id) throws SQLException;
}
