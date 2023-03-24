package com.example.jpa.controller;

import com.example.jpa.entity.Car;
import com.example.jpa.entity.Person;
import com.example.jpa.repo.CarRepo;
import com.example.jpa.repo.PersonRepo;
import com.example.jpa.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v2")
@RestControllerAdvice
public class PersonController {
    @Autowired private JpaService jpaService;
    @Autowired private CarRepo carRepo;

    @Autowired private PersonRepo personRepo;

    @GetMapping(path = "")
    public ResponseEntity getAllPerson() {
        return ResponseEntity.ok(jpaService.getAllByPage());
    }

    @GetMapping(path = "/1")
    public ResponseEntity getAllPersonById() {
        Person person = personRepo.findById(1L).get();
        List<Car> cars = person.getCars();
        if(cars.size() > 0) {
            System.out.println(cars);
        }
        System.out.println("fdsf");
        return ResponseEntity.ok(person);
    }

    @PostMapping (path = "")
    public ResponseEntity addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(person);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updatePerson(@PathVariable Long id, @RequestBody Person person) {
//        Person person = new Person();
        jpaService.updatePerson(person, id);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        jpaService.delete(id);
        return ResponseEntity.ok("success!");
    }

    @GetMapping(path = "/car")
    public ResponseEntity getCar() {
        return ResponseEntity.ok(carRepo.findAll());
    }
}
