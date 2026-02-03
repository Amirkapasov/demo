package aitu.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
}
//| Метод | URL                        |
//        | ------ | -------------------------- |
//        | GET    | /students                  |
//        | POST   | /students                  |
//        | PUT    | /students/{id}             |
//        | DELETE | /students/{id}             |
//        | GET    | /students/search?name=alex |
//        | GET    | /students/sort/age         |
