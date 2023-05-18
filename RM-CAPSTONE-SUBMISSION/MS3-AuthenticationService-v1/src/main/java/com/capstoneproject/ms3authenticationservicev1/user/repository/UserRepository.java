package com.capstoneproject.ms3authenticationservicev1.user.repository;

import com.capstoneproject.ms3authenticationservicev1.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.userName = :userName AND u.role='admin'")
    UserEntity findUserByUserNameAndAdmin(String userName);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.userName = :userName")
    UserEntity findUserByUserName(String userName);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.id = :id")
    UserEntity findUserById(long id);

}
