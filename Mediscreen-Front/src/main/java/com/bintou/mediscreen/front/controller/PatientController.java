package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.Patient;
import com.bintou.mediscreen.front.proximity.PatientProximity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class PatientController {

    private final PatientProximity patientProximity;

    @Autowired
    public PatientController(PatientProximity patientProximity) {
        this.patientProximity = patientProximity;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/patients")
    public String findAllPatients(Model model) {
        List<Patient> patients = patientProximity.findAllPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }

    @RolesAllowed("USER")
    @GetMapping("/patient")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }

    @RolesAllowed("USER")
    @PostMapping("/patient")
    public String validatePatient(@Valid Patient patient, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "patient/add";
        }
        this.patientProximity.createdPatient(patient);
        return "redirect:/api/patients";
    }

    @RolesAllowed("USER")
    @GetMapping("/patient/{id}")
    public String updatePatientForm(@PathVariable("id") Long id, Model model) {

        log.debug(" *** Méthode - GET /api/patient/{id} : {}", id);

        Patient patient = this.patientProximity.findPatientById(id);
        model.addAttribute("patient", patient);

        log.info(" *** Page de mise à jour du patient a été chargée avec succès");

        return "patient/update";
    }

    @PostMapping("/patient/{id}")
    public String updatePatient(@PathVariable("id") Long id, @Valid Patient patient, BindingResult result) {

        log.debug(" *** Méthode - POST /api/patient/{id} : {}", id);
        if (result.hasErrors()) {
            return "patient/update";
        }
        this.patientProximity.updatePatient(id, patient);

        log.info(" *** Le patient a été mis à jour avec succès");

        return "redirect:/api/patients";
    }

    @RolesAllowed("USER")
    @GetMapping("/patients/patient/family")
    public String findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, Model model) {
        List<Patient> patients = patientProximity.findPatientByLastNameAndFirstName(lastName, firstName);
        if (patients.isEmpty()) {
            throw new IllegalArgumentException("Impossible de trouver le(s) patient(s) avec ce nom de famille : " + lastName + " et ce prénom : " + firstName);
        }
        model.addAttribute("patients", patients);
        return "redirect:/api/patients";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/patients/patientId/{id}")
    public String deletePatientById(@PathVariable("id") Long id, Model model) {

        Patient patient = patientProximity.findPatientById(id);
        if (patient != null) {
            patientProximity.deletePatientById(id);
            model.addAttribute("patients", patientProximity.findAllPatients());
            return "redirect:/api/patients";
        } else {
            throw new IllegalArgumentException("Il n'existe pas de patient avec cet id : " + id);
        }
    }


}
