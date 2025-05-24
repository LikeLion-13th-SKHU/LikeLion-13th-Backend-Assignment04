package likelion.likelionassignment04.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CountrySaveDto(
    @Size(min = 2, max = 10 , message = "국가는 2자 이상 10자 이하로 입력해야 합니다.")
    @NotBlank(message = "국가는 필수로 입력해야 합니다.")
    @NotEmpty(message = "nullish 빈 값(길이 0)을 허용하지 않습니다.")
    String country_name
) {
}
