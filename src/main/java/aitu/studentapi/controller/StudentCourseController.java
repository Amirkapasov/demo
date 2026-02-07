package aitu.studentapi.controller;

import aitu.studentapi.domain.Course;
import aitu.studentapi.repository.StudentCourseRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentCourseController {

    private final StudentCourseRepository repo;

    public StudentCourseController(StudentCourseRepository repo) {
        this.repo = repo;
    }

    // ➕ назначить курс студенту
    @PostMapping("/{studentId}/courses/{courseId}")
    public String addCourseToStudent(
            @PathVariable int studentId,
            @PathVariable int courseId
    ) throws SQLException {
        repo.addCourseToStudent(studentId, courseId);
        return "Course added to student";
    }

    // 📚 получить курсы студента
    @GetMapping("/{studentId}/courses")
    public List<Course> getCoursesByStudent(
            @PathVariable int studentId
    ) throws SQLException {
        return repo.findCoursesByStudent(studentId);
    }
}
