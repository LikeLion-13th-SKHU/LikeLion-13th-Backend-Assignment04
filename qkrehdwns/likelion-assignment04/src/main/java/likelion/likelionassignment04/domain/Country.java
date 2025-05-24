package likelion.likelionassignment04.domain;

import jakarta.persistence.*;
import likelion.likelionassignment04.dto.country.CountrySaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "country_name")
    private String country_name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities = new ArrayList<>();

    @Builder
    private Country(String country_name) {
        this.country_name = country_name;
    }

    public void update(CountrySaveDto countrySaveDto) {
        this.country_name = countrySaveDto.country_name();
    }


}
