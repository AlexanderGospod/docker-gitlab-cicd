package com.cucumber.frame.api.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class User {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
