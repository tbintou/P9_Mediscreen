package com.bintou.mediscreen.rapport.controller;

import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.service.RapportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "API des données du rapport")
@Slf4j
@RequestMapping("/api")
public class RapportController {

    private final RapportService rapportService;

    public RapportController(RapportService rapportService) {
        this.rapportService = rapportService;
    }

    @GetMapping("/reports/patient")
    @ApiOperation(value = "Obtenir un rapport sur le patient avec son nom et son prénom")
    public ResponseEntity<Rapport> getRapportByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {
        log.info("Obtenir le(s) Rapport(s) avec nom de famille : {} et prénom : {} ", lastName, firstName);
        Rapport rapport = rapportService.getRapportByLastNameAndFirstName(lastName, firstName);
        return new ResponseEntity<>(rapport, HttpStatus.OK);
    }

    @GetMapping("/reports/{id}")
    @ApiOperation(value = "Obtenir un rapport avec ID")
    public ResponseEntity<Rapport> getRapportById (@PathVariable long id) {
        log.info("Obtenir le(s) Rapport(s) avec ID : {} OK ", id);
        Rapport rapport = rapportService.getRapportById(id);
        return new ResponseEntity<>(rapport, HttpStatus.OK);
    }

}
