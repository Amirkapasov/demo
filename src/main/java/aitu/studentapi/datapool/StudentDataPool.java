package aitu.studentapi.datapool;

import aitu.studentapi.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDataPool {

    private final List<Student> students = new ArrayList<>();

    public void add(Student s) {
        students.add(s);
    }

    public List<Student> highGpa(float min) {
        return students.stream()
                .filter(s -> s.getGpa() > min)
                .collect(Collectors.toList());
    }

    public List<Student> sortByAge() {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .toList();
    }
}
