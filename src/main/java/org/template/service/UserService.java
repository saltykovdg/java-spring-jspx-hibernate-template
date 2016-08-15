package org.template.service;

import org.template.dao.RoleRepository;
import org.template.dao.UserRepository;
import org.template.entity.RoleEntity;
import org.template.entity.UserEntity;
import org.template.enums.RoleType;
import org.template.exceptions.ValidationException;
import org.template.util.Constants;
import org.template.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService extends AbstractService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity getUserById(String id) {
        return userRepository.findOne(id);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity signUpUser(UserDto userDto) throws ValidationException {
        validateUser(userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        RoleEntity roleUser = roleRepository.findByName(RoleType.USER.getName());
        userEntity.setRoles(Collections.singletonList(roleUser));
        userEntity.setBlocked(false);
        userRepository.save(userEntity);
        return userEntity;
    }

    private void validateUser(UserDto userDto) throws ValidationException {
        UserEntity userEntity = getUserByEmail(userDto.getEmail());
        if (userEntity != null) {
            throw new ValidationException(Constants.Messages.PAGE_SIGN_UP_ERROR_EMAIL_EXISTS);
        }
    }
}
