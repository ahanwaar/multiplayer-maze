package com.worldnavigator.multiplayermaze.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;



@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @NotBlank(message = "username cannot be empty")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "password cannot be empty")
    @Length(min=6, max=10, message="length of password should between 6 to 10 characters")
    private String password;

    public User(String username,
                String password
    ) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

