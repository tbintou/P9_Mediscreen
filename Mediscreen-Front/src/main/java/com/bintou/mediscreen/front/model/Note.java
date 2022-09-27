package com.bintou.mediscreen.front.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Note {

    private String id;

    private Long patientId;

    private String patientLastName;

    private String patientFirstName;

    @NotBlank(message = "Le champ note est obligatoire")
    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNote;

}
