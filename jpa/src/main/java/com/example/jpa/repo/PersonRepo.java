package com.example.jpa.repo;

import com.example.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends BaseRepo<Person, Long> {

}
