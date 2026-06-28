package SpringMVC.dto;

import java.time.LocalDate;

public record BookViewDto(
        String name,
        String author,
        LocalDate createdAt
) {
}
