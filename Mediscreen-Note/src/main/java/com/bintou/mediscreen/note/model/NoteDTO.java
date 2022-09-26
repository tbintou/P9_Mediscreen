package com.bintou.mediscreen.note.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "note")
public class NoteDTO {

    private String id;

    private Integer patientId;

    private String patientLastName;

    private String patientFirstName;

    private LocalDate dateNote;

    private String note;

    public NoteDTO(String id, Integer patientId, LocalDate dateNote, String note) {

    }
    public NoteDTO(String id, Integer patientId, String patientLastName, String patientFirstName, LocalDate dateNote, String note) {
        this.id = id;
        this.patientId = patientId;
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.dateNote = dateNote;
        this.note = note;
    }

    public NoteDTO(int i, LocalDate date, String s) {
    }
}
