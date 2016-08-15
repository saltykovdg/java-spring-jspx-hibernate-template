package org.template.dao;

import org.template.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}