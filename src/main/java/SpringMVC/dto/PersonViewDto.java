package SpringMVC.dto;

import SpringMVC.entity.Book;

import java.time.LocalDate;
import java.util.List;

public record PersonViewDto(
        Long id,
        String name,
        LocalDate born,
        List<BookViewDto> books
)
{
}
