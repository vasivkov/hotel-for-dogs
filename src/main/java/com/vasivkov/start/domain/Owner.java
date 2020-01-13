package com.vasivkov.start.domain;

public class Owner {
    private String name;
    private String LastName;

    public Owner() {
    }

    public Owner(String name, String lastName) {
        this.name = name;
        LastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return LastName + " " + name;
    }
}
