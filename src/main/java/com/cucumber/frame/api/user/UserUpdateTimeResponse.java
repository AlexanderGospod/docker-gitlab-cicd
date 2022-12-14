package com.cucumber.frame.api.user;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class UserUpdateTimeResponse extends UserUpdateTime {

    private UserUpdateTimeResponse(String name, String job){
        super(name, job);
    }
    private String updatedAt;
    public UserUpdateTimeResponse(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }
}
