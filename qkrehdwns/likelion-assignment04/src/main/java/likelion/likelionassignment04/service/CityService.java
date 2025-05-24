package likelion.likelionassignment04.service;


import likelion.likelionassignment04.common.error.ErrorCode;
import likelion.likelionassignment04.common.exception.BusinessException;
import likelion.likelionassignment04.domain.City;
import likelion.likelionassignment04.domain.Country;
import likelion.likelionassignment04.dto.city.CityResponseDto;
import likelion.likelionassignment04.dto.city.CitySaveDto;
import likelion.likelionassignment04.repository.CityRepository;
import likelion.likelionassignment04.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    // 도시 저장
    @Transactional
    public void saveCity(CitySaveDto citySaveDto) {
       Country country = countryRepository.findById(citySaveDto.country_id())
           .orElseThrow(() -> new BusinessException(ErrorCode.CITY_NOT_FOUND_EXCEPTION,
               ErrorCode.CITY_NOT_FOUND_EXCEPTION.getMessage() + citySaveDto.country_id()));

        City city = City.create(citySaveDto.city_name(),country);

        cityRepository.save(city);
    }

    //특정 국가의 도시들 조회
    @Transactional
    public Page<CityResponseDto> findCitiesByCountryId(Long countryId, Pageable pageable) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.CITY_NOT_FOUND_EXCEPTION,
                ErrorCode.CITY_NOT_FOUND_EXCEPTION.getMessage() + countryId));

        Page<City> cities = cityRepository.findByCountry(country, pageable);

        return cities.map(CityResponseDto::from);
    }

    @Transactional
    public void updateCity(Long cityId, CitySaveDto citySaveDto) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new BusinessException(ErrorCode.CITY_NOT_FOUND_EXCEPTION,
                ErrorCode.CITY_NOT_FOUND_EXCEPTION.getMessage() + cityId));

        city.update(citySaveDto);
    }

    @Transactional
    public void deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new BusinessException(ErrorCode.CITY_NOT_FOUND_EXCEPTION,
                ErrorCode.CITY_NOT_FOUND_EXCEPTION.getMessage() + cityId));

        cityRepository.delete(city);
    }
}
