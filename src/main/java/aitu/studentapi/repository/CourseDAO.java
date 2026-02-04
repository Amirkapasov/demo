package aitu.studentapi.repository;

import aitu.studentapi.config.DatabaseConfig;
import aitu.studentapi.domain.Course;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDAO implements CourseRepository {

    @Override
    public List<Course> findAll() throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection c = DatabaseConfig.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("credits")
                ));
            }
        }
        return list;
    }

    @Override
    public Course findById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id=?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("credits")
                );
            }
        }
        return null;
    }

    @Override
    public void save(Course c) throws SQLException {
        String sql = "INSERT INTO courses(title, credits) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitle());
            ps.setInt(2, c.getCredits());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM courses WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
