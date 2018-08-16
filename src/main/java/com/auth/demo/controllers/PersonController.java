package com.auth.demo.controllers;


import com.auth.demo.model.Person;
import com.auth.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Default action for PersonManager: return all people
     *
     * @return The list of all people
     */
    @GetMapping("/list")
    public synchronized List<Person> getPersonList() {

        List<Person> personList = this.personService.getPersonList();
        return personList;
    }

    /**
     * Get sort ASC fistname
     */
    @GetMapping("/sortfirstname")
    public synchronized List<Person> getPersonByFistname() {
        List<Person> sortFirstname = this.personService.sortByFirstname();
        return sortFirstname;
    }

    /**
     * Get sort ASC lastname
     */
    @GetMapping("/sortlastname")
    public synchronized List<Person> getPersonByLastname() {
        List<Person> sortLastname = this.personService.sortByLastname();
        return sortLastname;
    }

    /**
     * Add a person
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Person createPerson(@RequestBody Person person) throws Exception {

        System.out.println(person.getEmail());

        if (!(personService.existPerson(person.getEmail()))) {
            this.personService.addPerson(person);
        } else {
            throw new Exception("person with " + person.getEmail() + " exists");
        }
        return person;
    }

    /**
     * Display a person's profile or find a person according his id
     */
    @RequestMapping(value = "email", method = RequestMethod.GET)
    public Person displayProfile(@RequestParam String email) {

        String message = null;
        Person personToShowed = this.personService.displayPerson(email);

        if (personService.existPerson(email)) {
            return personToShowed;
        } else {
            message = "' " + email + " ' does'nt exist in the DB";
        }
        return personToShowed;
    }

    /**
     * Update person data
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Person setProfile(@RequestBody Person person) {

        String message = null;
        Person personUpdated = this.personService.updatePerson(person);

        if (personUpdated != null) {
            message = "Data have been updated";
        } else {
            message = "' " + person.getEmail() + " ' does'nt exist in the DB";
        }
        return personUpdated;
    }

    /**
     * Remove a person
     */
    @RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("email") String email) {

        String message = null;
        Person personDeleted = this.personService.deletePerson(email);

        if (personDeleted != null) {
            message = "Person " + personDeleted.getFirstname() + " " + personDeleted.getLastname() + " has been deleted";
        } else {
            message = "Person " + email + " does'nt exist in the DB";
        }
        return message;
    }

    /**
     * Check a person in DB
     */
    @RequestMapping(value = "/verification/{email}", method = RequestMethod.GET)
    public boolean existPerson(String email) {

        return this.personService.existPerson(email);
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public Person displayPersonById(String id) {

        return this.personService.displayPersonById(id);
    }
}
