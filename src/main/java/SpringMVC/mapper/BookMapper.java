package SpringMVC.mapper;

import SpringMVC.dto.BookViewDto;
import SpringMVC.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookViewDto fromBookToBookViewDto(Book book);
}
