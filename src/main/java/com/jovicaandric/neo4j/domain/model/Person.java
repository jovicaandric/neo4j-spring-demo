package com.jovicaandric.neo4j.domain.model;

public class Person {

    private String name;
    private int born;

    public Person(final String name, final int born) {
        this.name = name;
        this.born = born;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(final int born) {
        this.born = born;
    }
}
