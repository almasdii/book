package SpringMVC.mapper;

import SpringMVC.dto.BookDto;
import SpringMVC.dto.BookViewDto;
import SpringMVC.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = PersonMapper.class)
public interface BookMapper {
    BookViewDto fromBookToBookViewDto(Book book);
    Book fromBookViewDtoToBook(BookViewDto bookViewDto);
    List<BookViewDto> fromBookListToBookViewDtoList(List<Book> books);

    Book fromBookDtoToBook(BookDto bookDto);
    BookDto fromBookToBookDto(Book book);




}
