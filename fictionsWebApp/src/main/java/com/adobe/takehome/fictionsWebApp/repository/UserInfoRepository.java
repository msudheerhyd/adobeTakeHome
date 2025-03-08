package com.adobe.takehome.fictionsWebApp.repository;

import com.adobe.takehome.fictionsWebApp.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByUsername(String username);
}
