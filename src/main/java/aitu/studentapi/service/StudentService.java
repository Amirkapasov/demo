package aitu.studentapi.service;

import aitu.studentapi.domain.Person;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements PersonService {

    @Override
    public void printInfo(Person person) {
        System.out.println(person.getName() + " is " + person.getAge() + " years old");
        person.printRole(); // полиморфизм вызова метода
    }
}
