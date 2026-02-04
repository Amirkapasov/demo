package aitu.studentapi.domain;

public abstract class Person {

    protected String name;
    protected int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void printRole() {
        System.out.println("I am a person");
    }
}
