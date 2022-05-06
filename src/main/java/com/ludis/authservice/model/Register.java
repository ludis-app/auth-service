package com.ludis.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Register {

    String email;
    String password;
    Role role;
}
