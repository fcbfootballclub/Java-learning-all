package com.example.jpa.service;

import com.example.jpa.common.Result;
import com.example.jpa.entity.Person;
import org.springframework.data.domain.Page;

public interface BaseService {
    Result<Page<Person>> getAllByPage();
}
