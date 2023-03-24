package com.example.jpa.service;

import com.example.jpa.common.Result;
import com.example.jpa.common.Status;
import com.example.jpa.entity.Car;
import com.example.jpa.entity.Person;
import com.example.jpa.repo.CarRepo;
import com.example.jpa.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JpaService implements JpaSerInterface{
    private PersonRepo personRepo;
    private CarRepo carRepo;

    public Result<Page<Person>> getAllByPage() {
        Result<Page<Person>> rs = new Result<>();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Person> all2 = personRepo.findAll(pageable);
        Status status = Status.SUCCESSFULLY;
        rs.setCode(status.getCode())
                .setMessage(status.getMessage())
                .setData(all2);
        return rs;
    }

    public Person findById(Long id) {
        Person person = personRepo.findById(id).orElse(null);
        return person;
    }

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public Person updatePerson(Person person, long id) {
        Person found = personRepo.findById(id).orElse(null);
        if(found != null) {
            found.setName(person.getName());
            found.setAge(person.getAge());
            found.setCars(person.getCars());
        }
        Person save = personRepo.save(found);
        return save;
    }

    public void delete(Long id) {
        Person person = personRepo.findById(id).orElse(null);
        if(person == null) return;
        List<Car> cars = person.getCars();
        if(cars.size() != 0) {
            for(Car car : cars) {
                car.setPerson(null);
            }
            carRepo.saveAll(cars);
        }
        System.out.println("fds");
        personRepo.deleteById(id);
    }
}
