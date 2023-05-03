package org.example.service;

import org.example.dto.User;
import org.example.entity.UserBalance;

import java.util.Optional;

public interface UserService {
    public User findUserById(Integer id);
}
