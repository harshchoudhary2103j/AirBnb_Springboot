package com.harshchoudhary.projects.AirBnb_SpringBoot.dto;


import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String email;
    private String password;
    private String name;

}
