package SpringMVC.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BookSelectedView(
        String name,
        String author,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate createdAt
) {
}
