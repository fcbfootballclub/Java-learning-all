package com.example.jpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T, K> extends JpaRepository<T, K> {
    Page<T> findAll(Pageable pageable);
}
