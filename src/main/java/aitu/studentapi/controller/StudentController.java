package aitu.studentapi.controller;

import aitu.studentapi.domain.Student;
import aitu.studentapi.repository.StudentRepository;
import aitu.studentapi.service.PersonService;
import aitu.studentapi.exception.StudentNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import aitu.studentapi.datapool.StudentDataPool;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;
    private final PersonService personService;
    private final StudentDataPool dataPool;

    // ✅ ОДИН конструктор
    public StudentController(StudentRepository repo,
                             PersonService personService,
                             StudentDataPool dataPool) {
        this.repo = repo;
        this.personService = personService;
        this.dataPool = dataPool;
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

        personService.printInfo(s);
        return "Printed to console";
    }

    @PostMapping
    public String add(@RequestBody Student s) throws SQLException {
        if (repo.existsByName(s.getName())) {
            return "Error: student with this name already exists";
        }
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
    @GetMapping("/high-gpa/{min}")
    public List<Student> highGpa(@PathVariable float min) throws SQLException {
        dataPool.setStudents(repo.findAll());
        return dataPool.highGpa(min);
    }

    @GetMapping("/sort/age")
    public List<Student> sortByAge() throws SQLException {
        dataPool.setStudents(repo.findAll());
        return dataPool.sortByAge();
    }

}

