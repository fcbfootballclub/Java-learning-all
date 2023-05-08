package org.payment.service;

import org.core.dto.UserDto;

public interface UserService {
    public UserDto findUserById(Integer id);
}
