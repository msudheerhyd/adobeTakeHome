package com.adobe.takehome.fictionsWebApp.service;

import com.adobe.takehome.fictionsWebApp.model.UserInfo;
import com.adobe.takehome.fictionsWebApp.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        return userInfoRepository.save(userInfo);
    }
}
