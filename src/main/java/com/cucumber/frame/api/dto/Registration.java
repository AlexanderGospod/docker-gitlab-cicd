package com.cucumber.frame.api.dto;

import lombok.Data;
@Data
public class Registration {
    private String email;
    private String password;
    private Integer id;
    private String token;
}
