package com.bintou.mediscreen.note.repository;

import com.bintou.mediscreen.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

    List<Note> findByPatientId(Long patientId);
    List<Note> findByPatientLastNameAndPatientFirstName(String patientLastName, String patientFirstName);
}
