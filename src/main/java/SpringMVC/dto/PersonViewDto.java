package SpringMVC.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PersonViewDto(
        Long id,
        String name,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate born
)
{
}
