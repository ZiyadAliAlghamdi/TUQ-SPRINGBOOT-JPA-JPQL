package org.example.jpaandjpql.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be null")
    @Size(min= 4, max = 40, message = "name length should be between 4 and 20 chars")
    @Column(columnDefinition = "varchar(40) not null")
    private String name;


    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, max = 20, message = "user length should be between 4 and 20")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password cannot empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*?&#]{6,}$", message = "password should be safe")
    @Size(min = 7,max = 30, message = "password length cannot be more than 30 and less than 7")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;


    @NotEmpty(message = "e-mail cannot be empty")
    @Email(message = "e-mail should have valid input 'mail@mail.com'")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String mail;


    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^\\b(user|admin)\\b$", message = "role must be (user|admin)")
    @Column(columnDefinition = "varchar(30) not null check(role IN('user','admin'))")
    private String role;

    @NotNull(message = "age cannot be null")
    @PositiveOrZero(message = "age cannot be negative")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
