package aitu.studentapi.repository;

import aitu.studentapi.config.DatabaseConfig;
import aitu.studentapi.domain.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO implements StudentRepository {

    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection c = DatabaseConfig.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                ));
            }
        }
        return list;
    }

    @Override
    public Student findById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                );
            }
        }
        return null;
    }

    @Override
    public void save(Student s) throws SQLException {
        String sql = "INSERT INTO students(name, age, gpa) VALUES (?, ?, ?)";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setFloat(3, s.getGpa());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(int id, Student s) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, gpa=? WHERE id=?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setFloat(3, s.getGpa());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }
    @Override
    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM students WHERE LOWER(name) = LOWER(?)";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM students WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
