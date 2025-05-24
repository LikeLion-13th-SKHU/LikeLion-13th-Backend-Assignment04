package likelion.likelionassignment04.dto.country;
import likelion.likelionassignment04.domain.Country;

public record CountryResponseDto(
    Long id,
    String country_name
) {
    public static CountryResponseDto from(Country country){
        return new CountryResponseDto(country.getId(), country.getCountry_name());
    }
}
