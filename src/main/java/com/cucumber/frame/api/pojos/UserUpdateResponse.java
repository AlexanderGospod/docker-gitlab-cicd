package com.cucumber.frame.api.pojos;

import com.cucumber.frame.api.model.UserUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class UserUpdateResponse extends UserUpdate {

    private UserUpdateResponse(String name, String job){
        super(name, job);
    }
    private String updatedAt;
    public UserUpdateResponse(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }
}
