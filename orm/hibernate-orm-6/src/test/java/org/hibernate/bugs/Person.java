package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Person {

    @Id
    private String id;

    @OneToOne
    private Person bestFriend;

    public Person() {

    }

    public Person(String id, Person bestFriend) {
        this.id = id;
        this.bestFriend = bestFriend;
    }

    public String getId() {
        return id;
    }
}