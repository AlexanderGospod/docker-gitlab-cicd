package com.cucumber.frame.api.pojos.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessfulRegistration {

    private Integer id;
    private String token;
}
