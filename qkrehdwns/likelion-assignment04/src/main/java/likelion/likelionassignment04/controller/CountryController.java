package likelion.likelionassignment04.controller;

import jakarta.validation.Valid;
import likelion.likelionassignment04.common.error.SuccessCode;
import likelion.likelionassignment04.common.template.ApiResTemplate;
import likelion.likelionassignment04.dto.country.CountryResponseDto;
import likelion.likelionassignment04.dto.country.CountrySaveDto;
import likelion.likelionassignment04.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/country/save")
    public ApiResTemplate<String> saveCountry(@RequestBody @Valid CountrySaveDto countrySaveDto) {

        countryService.saveCountry(countrySaveDto);

        return ApiResTemplate.successResponse(SuccessCode.COUNTRY_SAVE_SUCCESS, SuccessCode.COUNTRY_SAVE_SUCCESS.getMessage());
    }

    @GetMapping("/country/all")
    public ApiResTemplate<Page<CountryResponseDto>> findAllCountries(@PageableDefault(size = 10, sort = "id",
                                                                                    direction = Sort.Direction.ASC)
                                                                     Pageable pageable) {
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, countryService.findAllCountries(pageable));
    }

    @GetMapping("/country/{countryId}")
    public ApiResTemplate<CountryResponseDto> findCountryById(@PathVariable Long countryId) {
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, countryService.findCountryById(countryId));
    }

    @PatchMapping("/country/{countryId}")
    public ApiResTemplate<String> updateCountry(@PathVariable Long countryId,
                                            @RequestBody CountrySaveDto countrySaveDto) {

        countryService.updateCountry(countryId, countrySaveDto);

        return ApiResTemplate.successResponse(SuccessCode.COUNTRY_UPDATE_SUCCESS, SuccessCode.COUNTRY_UPDATE_SUCCESS.getMessage());
    }

    @DeleteMapping("/country/{countryId}")
    public ApiResTemplate<String> deleteCountry(@PathVariable Long countryId) {

        countryService.deleteCountry(countryId);

        return ApiResTemplate.successResponse(SuccessCode.COUNTRY_DELETE_SUCCESS, SuccessCode.COUNTRY_DELETE_SUCCESS.getMessage());
    }
}
