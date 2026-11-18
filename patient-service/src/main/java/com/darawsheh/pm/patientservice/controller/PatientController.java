package com.darawsheh.pm.patientservice.controller;

import com.darawsheh.pm.patientservice.DTO.PatientRequestDTO;
import com.darawsheh.pm.patientservice.DTO.PatientResponseDTO;
import com.darawsheh.pm.patientservice.DTO.validator.CreatePatientValidationGroup;
import com.darawsheh.pm.patientservice.service.PatientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients=patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO=patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO, @PathVariable("id") UUID patientID) {
        PatientResponseDTO patientResponseDTO=patientService.updatePatient(patientID,patientRequestDTO);
        return  ResponseEntity.ok().body(patientResponseDTO);
    }
}
