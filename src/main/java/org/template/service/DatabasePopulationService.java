package org.template.service;

import org.template.dao.RoleRepository;
import org.template.dao.UserRepository;
import org.template.entity.RoleEntity;
import org.template.entity.UserEntity;
import org.template.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class DatabasePopulationService extends AbstractService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init(){
        logger.info("Start population database");
        createRoles();
        createUsers();
        logger.info("Finished population database");
    }

    private void createRoles() {
        createRole(RoleType.ADMIN.getName());
        createRole(RoleType.USER.getName());
    }

    private void createRole(String name) {
        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity();
            role.setName(name);
            roleRepository.save(role);
        }
    }

    private void createUsers() {
        RoleEntity roleAdmin = roleRepository.findByName(RoleType.ADMIN.getName());
        createUser("admin@mail.com", "admin@mail.com", Collections.singletonList(roleAdmin));
        RoleEntity roleUser = roleRepository.findByName(RoleType.USER.getName());
        createUser("user@mail.com", "user@mail.com", Collections.singletonList(roleUser));
    }

    private void createUser(String email, String password, List<RoleEntity> roles) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            user = new UserEntity();
            user.setBlocked(false);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
