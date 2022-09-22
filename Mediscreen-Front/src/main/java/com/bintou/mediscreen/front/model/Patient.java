package com.bintou.mediscreen.front.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Patient {

    private Long id;

    @Pattern(regexp="^[A-Za-z]*$", message = "Le prénom doit être de type text sans espace")
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 26, message = "La longueur du prénom doit être de 2 à 26 caractères")
    private String firstName;

    @Pattern(regexp="^[A-Za-z]*$", message = "Le nom de famille doit être de type text sans espace")
    @NotBlank(message = "Le nom de famille est obligatoire")
    @Size(min = 2, max = 26, message = "La longueur du nom de famille doit être de 2 à 26 caractères")
    private String lastName;

    @Past(message = "Veuillez entrer une date de naissance valide")
    @NotNull(message = "La date de naissance est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotNull(message = "Le genre du sexe est obligatoire")
    private String gender;

    private String adress;

    private String phone;

}

