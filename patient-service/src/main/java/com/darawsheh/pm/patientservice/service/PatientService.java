package com.darawsheh.pm.patientservice.service;

import com.darawsheh.pm.patientservice.DTO.PatientRequestDTO;
import com.darawsheh.pm.patientservice.DTO.PatientResponseDTO;
import com.darawsheh.pm.patientservice.mapper.PatientMapper;
import com.darawsheh.pm.patientservice.model.Patient;
import com.darawsheh.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients= patientRepository.findAll();

        return patients.stream().map(
                PatientMapper::toDTO
        ).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patient){
        Patient newPatient=patientRepository.save(
                PatientMapper.toModel(patient)
        );

        return PatientMapper.toDTO(newPatient);
    }
}
