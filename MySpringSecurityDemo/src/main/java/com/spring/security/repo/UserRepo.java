package com.spring.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.entity.UserEntity;

import com.spring.security.repo.UserRepo;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByUsername(String username);
}
