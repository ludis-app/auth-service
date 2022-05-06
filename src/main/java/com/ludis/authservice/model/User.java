package com.ludis.authservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int id;

    private String real_name;
    private String username;
    private String email;
    private String password;
    private String country;

    private Role role;
}
