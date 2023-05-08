package org.payment.service.Imp;

import org.core.dto.UserDto;
import org.core.exception.UserNotFoundException;
import org.payment.repository.UserRepository;
import org.payment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDto findUserById(Integer id) {
         return userRepository.findById(id)
                 .map(obj -> modelMapper.map(obj, UserDto.class))
                 .orElseThrow(() -> new UserNotFoundException("User not found id " + id));
    }
}
