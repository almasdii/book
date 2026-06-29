package SpringMVC.dto;

import java.time.LocalDate;

public record BookDto(
        String name,
        String author,
        LocalDate createdAt
) {
}
