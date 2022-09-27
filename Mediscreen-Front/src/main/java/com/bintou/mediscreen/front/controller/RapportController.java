package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.Rapport;
import com.bintou.mediscreen.front.proximity.RapportProximity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@Slf4j
public class RapportController {

    private final RapportProximity rapportProximity;


    @Autowired
    public RapportController(RapportProximity rapportProximity) {
        this.rapportProximity = rapportProximity;
    }

    @GetMapping("/rapport/{id}")
    public String getPatientRapport(@PathVariable("id") final Long patientId, final Model model) {

        log.debug(" *** Méthode - GET /api/rapport/{id}");
        model.addAttribute("patientId", patientId);
        model.addAttribute("rapport", this.rapportProximity.getPatientRapport(patientId));

        log.debug(" *** Rapport d'évaluation retourné avec succès");

        return "rapport/rapport";
    }

}
