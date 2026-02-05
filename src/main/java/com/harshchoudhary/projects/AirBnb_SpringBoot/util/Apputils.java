package com.harshchoudhary.projects.AirBnb_SpringBoot.util;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Users.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Apputils {
    public static User getCurrentUser(){

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
