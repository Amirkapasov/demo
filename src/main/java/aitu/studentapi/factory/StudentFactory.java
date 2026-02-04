package aitu.studentapi.factory;

import aitu.studentapi.domain.Student;

public class StudentFactory {
    public static Student create(String name, int age, float gpa) {
        return new Student(name, age, gpa);
    }
}
