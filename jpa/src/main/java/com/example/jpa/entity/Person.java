package com.example.jpa.entity;

import com.example.jpa.annotation.IpAddress;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "person")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "this must not be empty")
    private String name;

    @Column(name = "age")
/*    @Min(value = 5, message = "this length must at least 5")
    @Max(25)*/
//    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", message = "Wrong IP address")
    @IpAddress
    private String age;

    @Column(name = "create_at")
    private Date createdAt;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Nullable
    @JsonManagedReference
    private List<Car> cars;

    @Nullable
    public List<Car> getCars() {
        if(cars == null) {
            cars = new ArrayList<>();
        }
        return cars;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCars(@Nullable List<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
