package com.cucumber.frame.api.accesstosite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Registration {
    private String email;
    private String password;
}
