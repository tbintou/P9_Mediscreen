package com.bintou.mediscreen.rapport.controller;

import com.bintou.mediscreen.rapport.service.RapportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class RapportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RapportService rapportService;

    @Test
    public void getRiskAssessmentTest() throws Exception {
        Long patientId = 20L;
        when(rapportService.calculateRiskByPatientId(patientId)).thenReturn("Aucun risque (None)");
        mockMvc.perform(get("/api/rapports/patient/20"))
                .andExpect(status().isOk());
    }

    @Test
    public void getRiskAssessmentReturn404Test() throws Exception {
        Long patientId = 1L;
        when(rapportService.calculateRiskByPatientId(patientId)).thenReturn(null);
        mockMvc.perform(get("/api/rapports/patient/20"))
                .andExpect(status().isBadRequest());
    }
}
