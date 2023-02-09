package com.cucumber.frame.api.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class Color {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

}
