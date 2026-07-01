package SpringMVC.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record PersonDetailsView(
        Long id,
        String name,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate born,
        List<BookSelectedView> books
) { }
