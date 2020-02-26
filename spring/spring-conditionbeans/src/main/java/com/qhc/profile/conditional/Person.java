package com.qhc.profile.conditional;

public class Person {
    private String name="jack";
    public Person() {
        System.out.println("Person is created by spring");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
