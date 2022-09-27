package com.bintou.mediscreen.note.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.model.NoteDTO;
import com.bintou.mediscreen.note.repository.NoteRepository;
import com.bintou.mediscreen.note.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenNoteApplication.class })
class NoteControllerTest {

    @MockBean
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    private NoteDTO noteDTO;

    private final static String NOTE_ADD_URL = "/api/notes/";
    private final static String NOTE_GET_URL = "/api/note/";
    private final static String NOTE_LIST_URL = "/api/notes/list/";
    private final static String NOTE_UPDATE_URL = "/api/notes/patient/";
    private final static String NOTE_DELETE_URL = "/api/notes/note/";

    private LocalDate date = LocalDate.now();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createdNoteTest() throws Exception {

        noteDTO = new NoteDTO();
        noteDTO.setId("632d9950d35a776550adfa4d");
        noteDTO.setPatientId(2L);
        noteDTO.setDateNote(date);
        noteDTO.setNote("note");

        when(noteService.saveNote(noteDTO)).thenReturn(noteDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(NOTE_ADD_URL)
                        .content(mapper.writeValueAsString(noteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void findNoteByIdTest() throws Exception {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId("632d9950d35a776550adfa4d");
        noteDTO.setPatientId(2L);
        noteDTO.setDateNote(date);
        noteDTO.setNote("note");

        when(noteService.findNoteById("632d9950d35a776550adfa4d")).thenReturn(noteDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(NOTE_GET_URL + "632d9950d35a776550adfa4d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("");
        verify(noteService).findNoteById("632d9950d35a776550adfa4d");
    }

    @Test
    public void findAllNoteTest() throws Exception {

        NoteDTO noteDTO1 = new NoteDTO();
        noteDTO1.setId("632d9950d35a776550adfa4d");
        noteDTO1.setPatientId(2L);
        noteDTO1.setDateNote(date);
        noteDTO1.setNote("note1");

        NoteDTO noteDTO2 = new NoteDTO();
        noteDTO2.setId("632d9b1777f6c14ebd00e4ee");
        noteDTO2.setPatientId(2L);
        noteDTO2.setDateNote(date);
        noteDTO2.setNote("note2");

        when(noteService.findAllNote(2L)).thenReturn(Arrays
                .asList(noteDTO1, noteDTO2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(NOTE_LIST_URL + 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(noteService).findAllNote(2L);
        assertThat(content).contains("note1", "note2");
    }

    @Test
    public void updateNoteTest() throws Exception {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId("632d9b1777f6c14ebd00e4ee");
        noteDTO.setPatientId(2L);
        noteDTO.setDateNote(date);
        noteDTO.setNote("note updated");

        when(noteService.updateNote(anyString(), any(NoteDTO.class))).thenReturn(noteDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(NOTE_UPDATE_URL + 2)
                        .content(mapper.writeValueAsString(noteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("note updated");
        verify(noteService).updateNote(anyString(), any(NoteDTO.class));
    }

    @Test
    public void deleteNoteByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(NOTE_DELETE_URL + "632d9b1777f6c14ebd00e4ee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService).deleteNoteById("632d9b1777f6c14ebd00e4ee");

    }

}

