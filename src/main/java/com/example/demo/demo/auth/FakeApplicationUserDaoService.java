package com.example.demo.demo.auth;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.demo.security.ApplicationUsersRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();


    }

    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "anna",
                        passwordEncoder.encode("password"),
                        STUDENT.simpleGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                    ),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password1"),
                        ADMIN.simpleGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "anna",
                        passwordEncoder.encode("password12"),
                        ADMINISTRATOR.simpleGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
                );
        return applicationUsers;
    }


}
