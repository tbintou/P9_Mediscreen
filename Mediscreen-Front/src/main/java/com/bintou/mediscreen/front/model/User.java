package com.bintou.mediscreen.front.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp ="^[A-Za-z-\\s+]*$", message = "Nom complet doit être de type text sans espace")
    @NotBlank(message = "Le nom complet est obligatoire")
    @Column(name = "fullname")
    private String fullname;

    @Pattern(regexp ="^[A-Za-z0-9_]*$", message = "Nom d'utilisateur doit être de type text avec un numéro et sans espace")
    @NotBlank(message = "Nom d'utilisateur est obligatoire")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role = "USER";

    public User(String username, String fullname, String password, String role) {
    }
}

