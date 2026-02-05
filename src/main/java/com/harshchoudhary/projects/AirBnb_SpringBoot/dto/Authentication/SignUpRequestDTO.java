package com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Authentication;


import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String email;
    private String password;
    private String name;

}
