package com.cucumber.frame.api.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Color {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

}
