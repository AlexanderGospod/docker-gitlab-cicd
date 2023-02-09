package com.cucumber.frame.api.pojos;

import com.cucumber.frame.api.model.UserUpdate;
import com.cucumber.frame.api.utils.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data

public class UserUpdateResponse extends UserUpdate {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime updatedAt;
}
