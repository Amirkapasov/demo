package aitu.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
}

//GET: /students
//POST: /students
//POST: /students/{id}
//DELETE: /students/{id}
//GET: /students/search?name=alex
//GET: /students/sort/age
