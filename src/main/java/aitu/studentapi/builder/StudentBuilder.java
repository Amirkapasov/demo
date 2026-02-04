package aitu.studentapi.builder;

import aitu.studentapi.domain.Student;

public class StudentBuilder {
    private String name;
    private int age;
    private float gpa;

    public StudentBuilder setName(String name) { this.name = name; return this; }
    public StudentBuilder setAge(int age) { this.age = age; return this; }
    public StudentBuilder setGpa(float gpa) { this.gpa = gpa; return this; }

    public Student build() {
        return new Student(name, age, gpa);
    }
}
