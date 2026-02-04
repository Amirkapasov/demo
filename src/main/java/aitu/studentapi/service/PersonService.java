package aitu.studentapi.service;

import aitu.studentapi.domain.Person;

public interface PersonService {
    void printInfo(Person person);

    default void sayHello() {
        System.out.println("Hello from PersonService");
    }
}
