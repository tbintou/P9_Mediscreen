package com.bintou.mediscreen.note.controller;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenNoteApplication.class })
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private NoteService noteService;

    private Note note = new Note();


    @BeforeAll
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createdNoteTestReturnOK() throws Exception {
        note.setId(6L);
        note.setPatientId(7L);
        note.setPatientLastName("Macron");
        note.setPatientFirstName("Bernard");
        note.setNote("Notes du patient");
        note.setDateNote(LocalDateTime.of(2022, 2, 1, 2, 10));

        when(noteService.saveNote(note)).thenReturn(note);
        mockMvc.perform(post("/api/notes/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.note").value("Notes du patient"));
    }

    @Test
    public void findNoteByIdTestReturnOK() throws Exception {
        note.setDateNote(LocalDateTime.of(4, 5, 7, 5, 9));
        note.setId(22L);
        note.setPatientId(32L);
        note.setPatientLastName("Macron");
        note.setPatientFirstName("Bernard");
        note.setNote("Notes du patient");

        when(noteService.findNoteById(22L)).thenReturn(note);
        mockMvc.perform(get("/api/notes/22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.note").value("Notes du patient"));
    }

    @Test
    public void findNoteByIdTestReturnNull() throws Exception {
        when(noteService.findNoteById(null)).thenReturn(new Note());
        mockMvc.perform(get("/api/notes/null")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void findNoteByLastNameAndFirstNameTestReturnOK() throws Exception {
        Note note1 = new Note();
        note1.setDateNote(LocalDateTime.of(4, 5, 7, 5, 9));
        note1.setId(22L);
        note1.setPatientId(32L);
        note1.setPatientLastName("Macron");
        note1.setPatientFirstName("Bernard");
        note1.setNote("Notes du patient 1");

        Note note2 = new Note();
        note2.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        note2.setId(26L);
        note2.setPatientId(29L);
        note2.setPatientLastName("Macron");
        note2.setPatientFirstName("Bernard");
        note2.setNote("Notes du patient 2");

        List<Note> noteList = new ArrayList<>();
        noteList.add(note1);
        noteList.add(note2);

        when(noteService.findNoteByLastNameAndFirstName("Macron", "Bernard")).thenReturn(noteList);
        mockMvc.perform(get("/api/notes/findByLastAndFirstName")
                        .param("lastName", "Macron")
                        .param("firstName", "Bernard")
                        .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..*").isNotEmpty())
                .andExpect(jsonPath("$.[0].patientLastName").value( "Macron"))
                .andExpect(jsonPath("$.[0].patientFirstName").value("Bernard"))
                .andExpect(jsonPath("$.[1].patientLastName").value("Macron"))
                .andExpect(jsonPath("$.[1].patientFirstName").value("Bernard"));
    }

    @Test
    public void updateNotesTestReturnOK() throws Exception {
        Note updateNote = new Note();
        updateNote.setDateNote(LocalDateTime.of(4, 5, 7, 5, 9));
        updateNote.setId(22L);
        updateNote.setPatientId(32L);
        updateNote.setPatientLastName("Macron");
        updateNote.setPatientFirstName("Bernard");
        updateNote.setNote("Mise à jour des notes du patient");

        when(noteService.updateNote(22L, updateNote)).thenReturn(updateNote);
        mockMvc.perform(put("/api/notes/22")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.note").value("Mise à jour des notes du patient"));
    }

    @Test
    public void findAllNoteTestReturnOK() throws Exception {
        Note note1 = new Note();
        note1.setDateNote(LocalDateTime.of(4, 5, 7, 5, 9));
        note1.setId(22L);
        note1.setPatientId(32L);
        note1.setPatientLastName("Macron");
        note1.setPatientFirstName("Bernard");
        note1.setNote("Notes du patient 1");

        Note note2 = new Note();
        note2.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        note2.setId(26L);
        note2.setPatientId(29L);
        note2.setPatientLastName("Boulanger");
        note2.setPatientFirstName("Xavier");
        note2.setNote("Notes du patient 2");

        List<Note> noteList = new ArrayList<>();
        noteList.add(note1);
        noteList.add(note2);

        when(noteService.findAllNote()).thenReturn(noteList);
        mockMvc.perform(get("/api/notes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..*").isNotEmpty())
                .andExpect(jsonPath("$.[0].note").value("Notes du patient 1"))
                .andExpect(jsonPath("$.[1].note").value("Notes du patient 2"));
    }

    @Test
    public void deleteNoteByIdTestReturnOK() throws Exception {
        when(noteService.deleteNoteById(26L)).thenReturn(true);
        mockMvc.perform(delete("/api/notes/26")
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteNoteByIdTestReturnNull() throws Exception {
        when(noteService.deleteNoteById(null)).thenReturn(false);
        mockMvc.perform(delete("/api/notes/null")
        ).andExpect(status().isBadRequest());
    }

}
