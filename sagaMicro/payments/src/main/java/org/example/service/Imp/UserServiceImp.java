package org.example.service.Imp;

import org.apache.kafka.common.errors.UnknownServerException;
import org.example.entity.UserBalance;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserBalance findUserById(Integer id) {
         return userRepository.findById(id)
                 .orElseThrow(() -> new UserNotFoundException("User not found id " + id));
    }
}
