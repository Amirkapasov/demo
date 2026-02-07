package aitu.studentapi.controller;

import aitu.studentapi.domain.Course;
import aitu.studentapi.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Course> getAll() throws SQLException {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable int id) throws SQLException {
        return repo.findById(id);
    }

    @PostMapping
    public String add(@RequestBody Course c) throws SQLException {
        repo.save(c);
        return "Course added";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        repo.delete(id);
        return "Deleted";
    }
}
