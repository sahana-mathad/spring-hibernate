package com.demo.cruddemo.dao;

import com.demo.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity
    private EntityManager entityManager;
    //Inject Entity Manager using Constructor Injection
   @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //Implement the save method

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent); //save the object to the database
    }

    @Override
    public Student findById(Integer id){
       return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
       //Create a query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by firstName asc",Student.class);

        // return the query results
       return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
       //Create a query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student where lastName=:theData",Student.class);
        theQuery.setParameter("theData",theLastName);
        //Return the query results
        return theQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Student theStudent) {
       entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void delete(Integer id) {
       Student theStudent = entityManager.find(Student.class,id);
       entityManager.remove(theStudent);

    }

    @Override
    @Transactional
    public int deleteAll() {
       //create a query
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();


        return numRowsDeleted;
    }

}
