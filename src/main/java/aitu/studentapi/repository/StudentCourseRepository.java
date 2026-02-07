package aitu.studentapi.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import aitu.studentapi.domain.Course;
import aitu.studentapi.config.DatabaseConfig;


@Repository
public class StudentCourseRepository {

    private final Connection conn;

    public StudentCourseRepository() throws SQLException {
        this.conn = DatabaseConfig.getConnection();
    }


    public void addCourseToStudent(int studentId, int courseId) throws SQLException {
        String sql =
                "INSERT INTO student_course (student_id, course_id) " +
                        "VALUES (?, ?) ON CONFLICT DO NOTHING";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);
        ps.setInt(2, courseId);
        ps.executeUpdate();
    }


    public List<Course> findCoursesByStudent(int studentId) throws SQLException {
        String sql = """
            SELECT c.id, c.title, c.credits
            FROM courses c
            JOIN student_course sc ON c.id = sc.course_id
            WHERE sc.student_id = ?
        """;


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();
        List<Course> courses = new ArrayList<>();

        while (rs.next()) {
            courses.add(new Course(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("credits")
            ));
        }
        return courses;
    }
}
