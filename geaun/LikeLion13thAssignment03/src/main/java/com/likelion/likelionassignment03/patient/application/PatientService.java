package com.likelion.likelionassignment03.patient.application;

import com.likelion.likelionassignment03.exception.BusinessException;
import com.likelion.likelionassignment03.exception.ErrorCode;
import com.likelion.likelionassignment03.hospital.domain.Hospital;
import com.likelion.likelionassignment03.hospital.domain.repository.HospitalRepository;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientUpdateRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientSaveRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientInfoResponseDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientListResponseDto;
import com.likelion.likelionassignment03.patient.domain.repository.Patient;
import com.likelion.likelionassignment03.patient.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public void savePatient(PatientSaveRequestDto patientSaveRequestDto) {
        Hospital hospital = hospitalRepository.findById(patientSaveRequestDto.hospitalId())
                .orElseThrow(() -> new BusinessException(ErrorCode.HOSPITAL_NOT_FOUND_EXCEPTION));

        Patient patient = Patient.builder()
                .age(patientSaveRequestDto.age())
                .causes(patientSaveRequestDto.causes())
                .hospital(hospital)
                .build();

        patientRepository.save(patient);
    }

    public PatientListResponseDto getPatientsByHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new BusinessException(ErrorCode.HOSPITAL_NOT_FOUND_EXCEPTION));

        List<Patient> patients = patientRepository.findByHospital(hospital);
        List<PatientInfoResponseDto> patientInfoResponseDtos = patients.stream()
                .map(PatientInfoResponseDto::from)
                .toList();

        return PatientListResponseDto.from(patientInfoResponseDtos);
    }

    public PatientInfoResponseDto findPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PATIENT_NOT_FOUND_EXCEPTION));

        return PatientInfoResponseDto.from(patient);
    }

    @Transactional
    public void updatePatient(Long patientId, PatientUpdateRequestDto patientUpdateRequestDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PATIENT_NOT_FOUND_EXCEPTION));

        patient.update(patientUpdateRequestDto);
    }

    @Transactional
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PATIENT_NOT_FOUND_EXCEPTION));

        patientRepository.delete(patient);
    }
}

