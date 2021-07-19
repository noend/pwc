package com.edu.mse.pwc.dtos;

import com.edu.mse.pwc.persistence.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
