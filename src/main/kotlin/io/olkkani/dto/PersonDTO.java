package io.olkkani.dto;

import java.util.UUID;

public class PersonDTO {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getUUID() {
        return UUID.randomUUID().toString();
    }
    public int myAge(){return age;}
}
