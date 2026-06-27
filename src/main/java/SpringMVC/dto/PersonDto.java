package SpringMVC.dto;

import java.time.LocalDate;

public record PersonDto(
        String name,
        LocalDate born
) {
}
