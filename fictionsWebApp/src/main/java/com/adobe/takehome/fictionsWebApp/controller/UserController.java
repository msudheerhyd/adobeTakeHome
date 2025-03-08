package com.adobe.takehome.fictionsWebApp.controller;

import com.adobe.takehome.fictionsWebApp.dto.AuthRequest;
import com.adobe.takehome.fictionsWebApp.model.UserInfo;
import com.adobe.takehome.fictionsWebApp.service.JwtService;
import com.adobe.takehome.fictionsWebApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo) {
        try {
            UserInfo addedUserInfo = userService.addUser(userInfo);
            return ResponseEntity.ok("User successfully added with username = "
                    + userInfo.getUsername());
        } catch (Exception e) {
            logger.error("An Error occured adding user with username "
                    + userInfo.getUsername());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error occured adding user with username + " + userInfo.getUsername());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        try {
           if (authentication.isAuthenticated()) {
               return ResponseEntity.ok("Successfully authenticated : token is below : \n"
                       + jwtService.generateToken(authRequest.getUsername()));
           } else {
               return ResponseEntity.ok("User not found");
           }
        } catch (Exception e) {
            logger.error("An Error occured authenticating user with username = " + authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured authenticating the user "
                    + authRequest.getUsername());
        }
    }
}
