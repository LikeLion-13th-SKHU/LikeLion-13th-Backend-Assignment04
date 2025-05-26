package com.likelion.likelionassignment03.hospital.api;

import com.likelion.likelionassignment03.common.error.SuccessCode;
import com.likelion.likelionassignment03.common.template.ApiResTemplate;
import com.likelion.likelionassignment03.hospital.api.dto.request.HospitalSaveRequestDto;
import com.likelion.likelionassignment03.hospital.api.dto.request.HospitalUpdateRequestDto;
import com.likelion.likelionassignment03.hospital.api.dto.response.HospitalInfoResponseDto;
import com.likelion.likelionassignment03.hospital.api.dto.response.HospitalListResponseDto;
import com.likelion.likelionassignment03.hospital.application.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResTemplate<String> saveHospital(@RequestBody HospitalSaveRequestDto requestDto) {
        hospitalService.saveHospital(requestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.HOSPITAL_SAVE_SUCCESS);
    }

    @GetMapping("/all")
    public ApiResTemplate<HospitalListResponseDto> fetchHospitalList(
            @PageableDefault(size = 10, sort = "hospitalId", direction = Sort.Direction.ASC) Pageable pageable) {

        HospitalListResponseDto hospitalListResponseDto = hospitalService.getAllHospitals(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, hospitalListResponseDto);
    }

    @GetMapping("/{hospitalId}")
    public ApiResTemplate<HospitalInfoResponseDto> getHospital(@PathVariable("hospitalId") Long hospitalId) {
        HospitalInfoResponseDto hospitalInfoResponseDto = hospitalService.getHospitalById(hospitalId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, hospitalInfoResponseDto);
    }

    @PatchMapping("/{hospitalId}")
    public ResponseEntity<String> hospitalDelete(
            @PathVariable("hospitalId") Long hospitalId,
            @RequestBody HospitalUpdateRequestDto hospitalUpdateRequestDto) {
        hospitalService.hospitalUpdate(hospitalId, hospitalUpdateRequestDto);
        return new ResponseEntity<>("병원 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{hospitalId}")
    public ResponseEntity<String> hospitalDelete(
            @PathVariable("hospitalId") Long hospitalId) {
        hospitalService.hospitalDelete(hospitalId);
        return new ResponseEntity<>("병원 삭제", HttpStatus.OK);
    }
}
