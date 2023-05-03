package org.example.service.Imp;

import org.apache.kafka.common.errors.UnknownServerException;
import org.example.dto.User;
import org.example.entity.UserBalance;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public User findUserById(Integer id) {
         return userRepository.findById(id)
                 .map(obj -> modelMapper.map(obj, User.class))
                 .orElseThrow(() -> new UserNotFoundException("User not found id " + id));
    }
}
