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
    @GetMapping("/patients/patient")
    public String addPatientForm(Patient patient) {
        return "patient/add";
    }

    @RolesAllowed("USER")
    @PostMapping("/patient/valid")
    public String validatePatient(@Valid Patient patient, BindingResult result, Model model) {

        if(!result.hasErrors()) {
            patientProximity.createdPatient(patient);
            model.addAttribute("patient", patientProximity.findAllPatients());
            return "redirect:/api/patients";
        }
        return "patient/add";
    }

    @RolesAllowed("USER")
    @GetMapping("/patients/patient/{id}")
    public String updatePatientForm(@PathVariable("id") Long id, Patient patient, Model model) {
        patient = patientProximity.findPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/patients/patient/{id}")
    public String updatePatient(@PathVariable("id") Long id, @Valid Patient patient, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "patient/update";
        }
        Boolean updated = patientProximity.updatePatient(id, patient);
        if(updated) {
            model.addAttribute("patient", patientProximity.findAllPatients());
        }
        return "redirect:/api/patients";
    }

   /* @RolesAllowed("USER")
    @GetMapping("/patients/patient/{id}")
    public String findPatientById(@PathVariable("id") Long id, Model model) {
        if (id == null){
            throw new IllegalArgumentException("Il n'existe pas de patient avec cet id : " + id);
        }
        Patient patient = patientProximity.findPatientById(id);
        model.addAttribute("patient", patient);
        return "redirect:/api/patients";
    }*/

    @RolesAllowed("USER")
    @GetMapping("/patients/patient/family")
    public String findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, Model model) {
        List<Patient> patientList = patientProximity.findPatientByLastNameAndFirstName(lastName, firstName);
        if (patientList.isEmpty()) {
            throw new IllegalArgumentException("Impossible de trouver le(s) patient(s) avec ce nom de famille : " + lastName);
        }
        model.addAttribute("patients", patientList);
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
