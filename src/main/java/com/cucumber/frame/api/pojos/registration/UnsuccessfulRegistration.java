package com.cucumber.frame.api.pojos.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnsuccessfulRegistration {
    private  String error;
}
