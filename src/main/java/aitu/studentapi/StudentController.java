package aitu.studentapi;

import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentDAO dao;

    public StudentController(StudentDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Student> getAll() throws SQLException {
        return dao.findAll();
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) throws SQLException {
        dao.save(student);
        return "Student added";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) throws SQLException {
        dao.deleteById(id);
        return "Deleted if existed";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) throws SQLException {
        dao.update(id, student);
        return "Student updated";
    }

    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) throws SQLException {
        return dao.findByName(name);
    }

    @GetMapping("/sort/age")
    public List<Student> sortByAge() throws SQLException {
        return dao.findAllSortedByAgeAsc();
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) throws SQLException {
        return dao.findById(id);
    }
    @PostMapping("/batch")
    public String addMany(@RequestBody List<Student> students) throws SQLException {
        for (Student s : students) {
            dao.save(s);
        }
        return "Students added: " + students.size();
    }


}
