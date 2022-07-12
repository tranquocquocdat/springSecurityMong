package com.techprimers.mongodb.springbootmongodbexample.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.relation.Role;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String password;
    private String teamName;

    public User(String email, String password, String teamName) {
        this.email = email;
        this.password = password;
        this.teamName = teamName;
    }
}
