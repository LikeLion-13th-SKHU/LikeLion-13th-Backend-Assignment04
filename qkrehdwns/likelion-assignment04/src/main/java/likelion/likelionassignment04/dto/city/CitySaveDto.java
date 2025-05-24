package likelion.likelionassignment04.dto.city;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CitySaveDto(
    @NotNull(message = "국가는 필수로 입력해야 합니다.")
    Long country_id,
    @NotNull(message = "도시는 필수로 입력해야 합니다.")
    @Size(min = 2, max = 10)
    String city_name
) {

}
