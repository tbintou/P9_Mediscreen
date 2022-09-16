package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.Patient;
import com.bintou.mediscreen.front.proximity.PatientProximity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class PatientController {

    private  PatientProximity patientProximity;


    @Autowired
    public PatientController(final PatientProximity patientProximity) {
        this.patientProximity = patientProximity;
    }

  /*  @RolesAllowed({"ADMIN", "USER"})
    @RequestMapping("/patients")
    public String home(Model model) {
        model.addAttribute("patients", patientProximity.findAllPatients());
        return "patient/list";
    }*/

}
