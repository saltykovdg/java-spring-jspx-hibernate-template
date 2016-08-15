package org.template.dao;

import org.template.entity.PersistentLoginEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersistentLoginRepository extends PagingAndSortingRepository<PersistentLoginEntity, String> {
    @Modifying
    @Transactional
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    void deleteBySeries(String series);

    PersistentLoginEntity findByUsername(String username);
    PersistentLoginEntity findBySeries(String series);
}