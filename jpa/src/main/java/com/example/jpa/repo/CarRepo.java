package com.example.jpa.repo;

import com.example.jpa.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends BaseRepo<Car, Long> {

}
