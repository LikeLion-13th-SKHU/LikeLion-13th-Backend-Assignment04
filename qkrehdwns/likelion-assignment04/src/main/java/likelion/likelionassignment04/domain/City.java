package likelion.likelionassignment04.domain;

import jakarta.persistence.*;
import likelion.likelionassignment04.dto.city.CitySaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city_name")
    private String city_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Builder
    private City(String city_name, Country country) {
        this.city_name = city_name;
        this.country = country;
    }

    public void update(CitySaveDto citySaveDto) {
        this.city_name = citySaveDto.city_name();
    }

    public static City create(String cityName, Country country) {
        return City.builder()
            .city_name(cityName)
            .country(country)
            .build();
    }

}
