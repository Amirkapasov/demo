package aitu.studentapi.controller;

import aitu.studentapi.domain.Student;
import aitu.studentapi.repository.StudentRepository;
import aitu.studentapi.service.PersonService;
import aitu.studentapi.exception.StudentNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;
    private final PersonService personService;

    // ОДИН конструктор для Spring
    public StudentController(StudentRepository repo, PersonService personService) {
        this.repo = repo;
        this.personService = personService;
    }

    @GetMapping
    public List<Student> getAll() throws SQLException {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) throws SQLException {
        Student s = repo.findById(id);
        if (s == null) throw new StudentNotFoundException(id);
        return s;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id) throws SQLException {
        Student s = repo.findById(id);
        if (s == null) throw new StudentNotFoundException(id);

        personService.printInfo(s); // полиморфизм
        return "Printed to console";
    }

    @PostMapping
    public String add(@RequestBody Student s) throws SQLException {
        repo.save(s);
        return "Student added";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Student s) throws SQLException {
        repo.update(id, s);
        return "Updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws SQLException {
        repo.delete(id);
        return "Deleted";
    }
    @PostMapping("/batch")
    public String addMany(@RequestBody List<Student> students) throws SQLException {
        for (Student s : students) {
            repo.save(s);
        }
        return "Students added: " + students.size();
    }
}
