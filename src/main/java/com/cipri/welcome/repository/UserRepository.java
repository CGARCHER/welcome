package com.cipri.welcome.repository;

import com.cipri.welcome.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByName(String name);

    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE %:nombre%")
    List<UserEntity> findByNameContaining(String nombre);

    @Transactional
    @Modifying
    //@Query("DELETE FROM UserEntity u WHEHRE u.name = :nombre")
    Integer deleteByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.appl = :appl WHERE u.name = :name")
    Integer updateApplByName(String name, String appl);



}
