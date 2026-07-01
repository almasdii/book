package SpringMVC.dto;

import java.time.LocalDate;

public record PersonCreateRequest(
        String name,
        LocalDate born
) {
}
