package com.demo.nisum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.nisum.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
}
