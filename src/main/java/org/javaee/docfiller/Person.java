package org.javaee.docfiller;


public class Person {
    private int id;
    private String firstName;
    private String lastName;


    public Person(int id, String firstName, String lastName, int age, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}

