package org.hibernate.bugs;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SuperCard<G extends SuperGeneration> {

    @Id
    private Long id;

    @ManyToOne
    private G generation;

    private double balance;
}