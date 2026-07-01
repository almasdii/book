package SpringMVC.dto;

import java.time.LocalDate;

public record BookSummaryView(
        Long id,
        String name,
        String author,
        LocalDate createdAt
) {
}
