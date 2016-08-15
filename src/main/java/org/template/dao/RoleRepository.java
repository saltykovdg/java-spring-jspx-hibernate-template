package org.template.dao;

import org.template.entity.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, String> {
    RoleEntity findByName(String name);
}