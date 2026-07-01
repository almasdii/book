package SpringMVC.dto;

import java.time.LocalDate;

public record BookDetailsView(
        Long id,
        String name,
        String author,
        LocalDate createdAt,
        PersonSummaryView person
){}
