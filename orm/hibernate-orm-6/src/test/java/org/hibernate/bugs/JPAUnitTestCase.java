package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void wrong_select_generated() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person friend = new Person("friend", null);
        Person person = new Person("person", friend);

        entityManager.persist(friend);
        entityManager.persist(person);

        entityManager.getTransaction().begin();

        List<Object[]> results = entityManager.createQuery("""
                        select p.id, f.id
                        from Person p
                        left join p.bestFriend f
                        order by p.id desc
                        """, Object[].class)
                .getResultList();

        Assert.assertEquals(person.getId(), results.get(0)[0]);
        Assert.assertEquals(friend.getId(), results.get(0)[1]);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
