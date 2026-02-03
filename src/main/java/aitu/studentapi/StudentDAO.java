package aitu.studentapi;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO {

    public void save(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, age, gpa) VALUES (?, ?, ?)";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setFloat(3, student.getGpa());
            ps.executeUpdate();
        }
    }
    public Student findById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                );
            }
        }
        return null; // если студент не найден
    }

    public List<Student> findAll() throws SQLException {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection c = DatabaseConfig.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                result.add(new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                ));
            }
        }
        return result;
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void update(int id, Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, gpa=? WHERE id=?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setFloat(3, student.getGpa());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public List<Student> findByName(String name) throws SQLException {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                ));
            }
        }
        return result;
    }

    public List<Student> findAllSortedByAgeAsc() throws SQLException {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY age ASC";

        try (Connection c = DatabaseConfig.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                result.add(new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getFloat("gpa")
                ));
            }
        }

        return result;
    }
}

