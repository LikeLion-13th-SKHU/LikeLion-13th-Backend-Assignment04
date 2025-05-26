package com.likelion.likelionassignment03.patient.api;

import com.likelion.likelionassignment03.common.response.ApiResTemplate;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientSaveRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientUpdateRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientListResponseDto;
import com.likelion.likelionassignment03.patient.application.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<ApiResTemplate<String>> savePatient(@RequestBody @Valid PatientSaveRequestDto requestDto) {
        patientService.savePatient(requestDto);
        return ResponseEntity.status(201)
                .body(ApiResTemplate.success("환자 저장 성공"));
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<ApiResTemplate<PatientListResponseDto>> getPatientsByHospital(
            @PathVariable("hospitalId") Long hospitalId) {
        PatientListResponseDto responseDto = patientService.getPatientsByHospitalId(hospitalId);
        return ResponseEntity.ok(ApiResTemplate.success("환자 목록 조회 성공", responseDto));
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<ApiResTemplate<String>> updatePatient(
            @PathVariable("patientId") Long patientId,
            @RequestBody PatientUpdateRequestDto requestDto) {
        patientService.updatePatient(patientId, requestDto);
        return ResponseEntity.ok(ApiResTemplate.success("환자 수정 성공"));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<ApiResTemplate<String>> deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok(ApiResTemplate.success("환자 삭제 성공"));
    }
}

    }
}

