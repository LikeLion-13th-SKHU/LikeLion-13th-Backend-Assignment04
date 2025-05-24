package likelion.likelionassignment04.service;

import likelion.likelionassignment04.common.error.ErrorCode;
import likelion.likelionassignment04.common.exception.BusinessException;
import likelion.likelionassignment04.domain.Country;
import likelion.likelionassignment04.dto.country.CountryResponseDto;
import likelion.likelionassignment04.dto.country.CountrySaveDto;
import likelion.likelionassignment04.repository.CityRepository;
import likelion.likelionassignment04.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    // 국가 저장
    @Transactional
    public void saveCountry(CountrySaveDto countrySaveDto) {
        Country country = Country.builder()
            .country_name(countrySaveDto.country_name())
            .build();

        countryRepository.save(country);
    }

    //모든 국가 조회
    @Transactional
    public Page<CountryResponseDto> findAllCountries(Pageable pageable) {
        Page<Country> cities = countryRepository.findAll(pageable);

        return cities.map(CountryResponseDto::from);
    }

    //단일 국가 조회
    @Transactional
    public CountryResponseDto findCountryById(Long countryId) {
        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION,
                ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION.getMessage() + countryId));

        return CountryResponseDto.from(country);
    }

    @Transactional
    public void updateCountry(Long countryId, CountrySaveDto countrySaveDto) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION,
                ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION.getMessage() + countryId));

        country.update(countrySaveDto);
    }

    @Transactional
    public void deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new BusinessException(ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION,
                ErrorCode.COUNTRY_NOT_FOUND_EXCEPTION.getMessage() + countryId));

        countryRepository.delete(country);
    }

}
