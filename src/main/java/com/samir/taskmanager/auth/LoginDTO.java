package com.samir.taskmanager.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    private String username;
    private String password;
}
