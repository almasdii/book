package SpringMVC.mapper;

import SpringMVC.dto.BookDetailsView;
import SpringMVC.dto.BookSummaryView;
import SpringMVC.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = PersonMapper.class)
public interface BookMapper {
    BookDetailsView fromBookToBookViewDto(Book book);
    Book fromBookViewDtoToBook(BookDetailsView bookDetailsView);
    List<BookDetailsView> fromBookListToBookViewDtoList(List<Book> books);

    Book fromBookDtoToBook(BookSummaryView bookSummaryView);
    BookSummaryView fromBookToBookSummaryView(Book book);
    List<BookSummaryView> fromBookListToBookSummaryViewList(List<Book> books);



}
