package com.bintou.mediscreen.rapport.controller;

import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.model.Status;
import com.bintou.mediscreen.rapport.service.RapportService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RapportControllerTest {

    @Test
    public void getRapportByLastNameAndFirstNameTest() {
        RapportService rapportService = mock(RapportService.class);
        when(rapportService.getRapportByLastNameAndFirstName(anyString(), anyString()))
                .thenReturn(new Rapport("Nicolas", "Gros", "M", 28L, Status.None));
        ResponseEntity<Rapport> actualRapportByLastAndFirstName = (new RapportController(rapportService))
                .getRapportByLastAndFirstName("Nicolas", "Gros");
        assertTrue(actualRapportByLastAndFirstName.getHeaders().isEmpty());
        assertTrue(actualRapportByLastAndFirstName.hasBody());
        assertEquals(HttpStatus.OK, actualRapportByLastAndFirstName.getStatusCode());
        verify(rapportService).getRapportByLastNameAndFirstName(anyString(), anyString());
    }

    @Test
    public void getRapportByIdTest() {
        RapportService rapportService = mock(RapportService.class);
        when(rapportService.getRapportById(anyInt())).thenReturn(new Rapport("Nicolas", "Gros", "M", 28L, Status.None));
        ResponseEntity<Rapport> actualRapportById = (new RapportController(rapportService)).getRapportById(1);
        assertTrue(actualRapportById.getHeaders().isEmpty());
        assertFalse(actualRapportById.hasBody());
        assertEquals(HttpStatus.OK, actualRapportById.getStatusCode());
        verify(rapportService).getRapportById(anyLong());
    }

}
