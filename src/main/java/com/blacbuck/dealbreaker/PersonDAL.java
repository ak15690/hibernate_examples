package com.blacbuck.dealbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class PersonDAL {

    @Autowired
    private EntityManager entityManager;


    @Autowired
    private PersonRepository personRepository;


    public List<Person> getPersonsListForAddressIdEager(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Address.class);
        Root o = q.from(Address.class);
        o.fetch("personList", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));

        Address address = (Address) this.entityManager.createQuery(q).getSingleResult();
        return address.getPersonList();
    }

    public Person getPersonsWithAddressLazy(Long id) {
        return personRepository.getOne(id);
    }

    public Person getPersonsWithAddressEager(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Person.class);
        Root o = q.from(Person.class);
        o.fetch("address", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));

        Person person = (Person) this.entityManager.createQuery(q).getSingleResult();
        return person;
    }
}
