package com.bintou.mediscreen.rapport.controller;

import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.service.RapportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Api(tags = "API des données du rapport")
@Slf4j
@RequestMapping("/api")
public class RapportController {

    private final RapportService rapportService;

    @Autowired
    public RapportController(RapportService rapportService) {
        this.rapportService = rapportService;
    }

    @GetMapping("/rapports/patient/{patientId}")
    @ApiOperation("Générer une évaluation du diabète par l'ID du patient")
    public ResponseEntity<String> getRiskAssessment(@PathVariable(value = "patientId") Long patientId) {
        String risk = rapportService.calculateRiskByPatientId(patientId);
        if (risk == null) {
            log.info("Aucune donnée trouvée avec ce patientId : " + patientId);
            return new ResponseEntity<>("Aucune donnée trouvée avec ce patientId : " + patientId, HttpStatus.BAD_REQUEST);
        }
        log.info("Les Données du rapport trouvée avec ce patientId est : " + patientId);
        return ResponseEntity.ok(risk);
    }
}
