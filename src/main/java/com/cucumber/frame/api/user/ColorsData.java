package com.cucumber.frame.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColorsData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

}
