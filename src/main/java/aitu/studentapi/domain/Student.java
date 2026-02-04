package aitu.studentapi.domain;

public class Student extends Person {

    private int id;
    private float gpa;

    public Student() {}

    public Student(int id, String name, int age, float gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public Student(String name, int age, float gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public float getGpa() { return gpa; }

    public void setId(int id) { this.id = id; }
    public void setGpa(float gpa) { this.gpa = gpa; }

    @Override
    public void printRole() {
        System.out.println("I am a student");
    }
}
