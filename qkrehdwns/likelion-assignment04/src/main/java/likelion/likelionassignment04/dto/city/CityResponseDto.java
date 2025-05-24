package likelion.likelionassignment04.dto.city;

import likelion.likelionassignment04.domain.City;
import lombok.Builder;

@Builder
public record CityResponseDto (
    Long country_id,
    String city_name
){
    public static CityResponseDto from(City city){
        return CityResponseDto.builder()
            .country_id(city.getCountry().getId())
            .city_name(city.getCity_name())
            .build();
    }
}
