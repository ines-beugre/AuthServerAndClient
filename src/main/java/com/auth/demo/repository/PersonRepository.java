package com.auth.demo.repository;


import com.auth.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByEmail(String email);
    public Person findById(String email);


}
