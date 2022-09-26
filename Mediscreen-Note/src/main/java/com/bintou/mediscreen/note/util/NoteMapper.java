package com.bintou.mediscreen.note.util;

import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;
import org.springframework.stereotype.Component;


@Component
public class NoteMapper {

    public Note toNote(final NoteDTO noteDTO) {

        Note note = new Note();
        note.setPatientId(noteDTO.getPatientId());
        note.setPatientLastName(noteDTO.getPatientLastName());
        note.setPatientFirstName(noteDTO.getPatientFirstName());
        note.setDateNote(noteDTO.getDateNote());
        note.setNote(noteDTO.getNote());

        return note;
    }

    public NoteDTO toNoteDTO(final Note note) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setPatientId(note.getPatientId());
        noteDTO.setPatientLastName(note.getPatientLastName());
        noteDTO.setPatientFirstName(note.getPatientFirstName());
        noteDTO.setDateNote(note.getDateNote());
        noteDTO.setNote(note.getNote());

        return noteDTO;
    }

}

