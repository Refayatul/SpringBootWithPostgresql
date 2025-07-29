package com.example.demo;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @PostMapping("/addPerson")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
       Person response = personRepo.save(person);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchAll")
    public List<Person> getAllPerson(){
        List<Person> PersonList = personRepo.findAll();
        return PersonList;
    }



}
