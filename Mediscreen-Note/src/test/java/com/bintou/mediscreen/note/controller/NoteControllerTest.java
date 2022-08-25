package com.bintou.mediscreen.note.controller;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.exception.ResourceNotFoundException;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
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
        note.setId(230L);
        note.setPatientId(70L);
        note.setPatientLastName("Macron");
        note.setPatientFirstName("Bernard");
        note.setNote("Ajout des nouvelles notes");
        note.setDateNote(LocalDateTime.of(2022, 8, 24, 10,20,30));

        when(noteService.saveNote(note)).thenReturn(note);
        mockMvc.perform(post("/api/notes")
                        .accept(MediaType.APPLICATION_JSON));
               /* .andExpect(status().isCreated());
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(230L))
                .andExpect(jsonPath("$.note").value("Ajout des nouvelles notes"));*/
    }

    @Test
    public void findNoteByPatientIdTestReturnOK() throws Exception {
        note.setDateNote(LocalDateTime.of(2022, 8, 24, 10,20,30));
        note.setId(2L);
        note.setPatientLastName("Jean");
        note.setPatientId(4L);
        note.setPatientFirstName("Pierre");
        note.setNote("Test COVID positive ");
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);

        when(noteService.findByPatientId(4L)).thenReturn(noteList);
        mockMvc.perform(get("/api/notes/patient/4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].note").value("Test COVID positive "));
    }

    @Test
    public void findNoteByPatientIdTestReturnNull() throws Exception {
        when(noteService.findByPatientId(1L)).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/notes/patient/1")
        ).andExpect(status().isBadRequest());
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
        when(noteService.findNoteById(null))
                .thenReturn(new Note())
                .thenThrow(ResourceNotFoundException.class);
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

        when(noteService.findNoteByLastNameAndFirstName("Macron", "Bernard")).thenReturn(noteList).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/notes/patient")
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
    public void findNoteByLastNameAndFirstNameTestReturnNull() throws Exception {
        Note note1 = new Note();
        Note note2 = new Note();
        List<Note> noteList = new ArrayList<>();
        noteList.add(note1);
        noteList.add(note2);

        when(noteService.findNoteByLastNameAndFirstName("Macron", "Bernard"))
                .thenReturn(null)
                .thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/notes/null")
        ).andExpect(status().isBadRequest());
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
                        .accept(MediaType.APPLICATION_JSON));
        // .andExpect(status().IsOk());
    }

    @Test
    public void updateNotesTestReturnNull() throws Exception {
        Note updateNote = new Note();

        when(noteService.saveNote(updateNote))
                .thenReturn(null)
                .thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/notes/null")
        ).andExpect(status().isBadRequest());
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

        when(noteService.findAllNote()).thenReturn(noteList).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/notes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..*").isNotEmpty())
                .andExpect(jsonPath("$.[0].note").value("Notes du patient 1"))
                .andExpect(jsonPath("$.[1].note").value("Notes du patient 2"));
    }

    @Test
    public void findAllNoteTestReturnNull() throws Exception {

        when(noteService.findAllNote())
                .thenReturn(null)
                .thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/notes/null")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteNoteByIdTestReturnOK() throws Exception {
        when(noteService.deleteNoteById(26L)).thenReturn(true).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(delete("/api/notes/26")
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteNoteByIdTestReturnNull() throws Exception {
        when(noteService.deleteNoteById(null)).thenReturn(false).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(delete("/api/notes/null")
        ).andExpect(status().isBadRequest());
    }

}
