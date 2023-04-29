package org.example.service;

import org.example.entity.UserBalance;

import java.util.Optional;

public interface UserService {
    public UserBalance findUserById(Integer id);
}
