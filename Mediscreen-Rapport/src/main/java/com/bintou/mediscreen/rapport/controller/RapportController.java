package com.bintou.mediscreen.rapport.controller;

import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.service.RapportServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Api(tags = "API des données du rapport")
@Slf4j
@RequestMapping("/api")
public class RapportController {

    private final RapportServiceImpl rapportService;

    @Autowired
    public RapportController(RapportServiceImpl rapportService) {
        this.rapportService = rapportService;
    }

    @GetMapping("/rapport/{id}")
    public Rapport getPatientRapport(@PathVariable("id") final Long patientId) {

        log.debug(" *** Méthode GET /api/rapport/{id} ");

        Rapport rapport = rapportService.getPatientRapport(patientId);

        log.debug(" *** Rapport d'évaluation renvoyé avec succès");

        return rapport;
    }

    @GetMapping("/rapport/family")
    public Rapport getPatientRapportByFamilyName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName) {

        log.debug(" *** GET /api/rapport/family?lastName=Jean&firstName=batis");

        Patient patient = rapportService.getPatient(lastName, firstName);

        Rapport rapport = rapportService.getPatientRapport(patient.getId());

        log.debug(" *** Rapport d'évaluation renvoyé avec succès");

        return rapport;
    }
}
