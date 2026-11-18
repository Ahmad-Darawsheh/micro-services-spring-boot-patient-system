package com.darawsheh.pm.patientservice.service;

import com.darawsheh.pm.patientservice.DTO.PatientRequestDTO;
import com.darawsheh.pm.patientservice.DTO.PatientResponseDTO;
import com.darawsheh.pm.patientservice.exception.EmailAlreadyExistsException;
import com.darawsheh.pm.patientservice.exception.PatientNotFoundException;
import com.darawsheh.pm.patientservice.mapper.PatientMapper;
import com.darawsheh.pm.patientservice.model.Patient;
import com.darawsheh.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if(patientRepository.existsByEmail(patient.getEmail())){
            throw new EmailAlreadyExistsException("The following email:"+ patient.getEmail()+" already exists");
        }

        Patient newPatient=patientRepository.save(
                PatientMapper.toModel(patient)
        );

        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient= patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: "+ id) );
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),patient.getId())){
            throw new EmailAlreadyExistsException("The following email:"+ patient.getEmail()+" already exists");
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patientRepository.save(patient);
        return PatientMapper.toDTO(patient);

    }

}
