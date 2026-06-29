package SpringMVC.controller;

import SpringMVC.dao.BookDao;
import SpringMVC.dto.BookDto;
import SpringMVC.dto.BookViewDto;
import SpringMVC.dto.PersonViewDto;
import SpringMVC.service.BookService;
import SpringMVC.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final BookDao bookDao;
    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public BookController(BookService service, BookDao bookDao, PersonService personService, BookService bookService) {
        this.service = service;
        this.bookDao = bookDao;
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping
    public String booksPage(Model model){
        List<BookViewDto> all = service.findAll();
        model.addAttribute("books",all);
        return "books";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") BookDto bookDto){
        return "newBook";
    }

    @PostMapping
    public String newBook(@ModelAttribute("book") BookDto bookDto){
        service.save(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookPage(Model model, @PathVariable("id") Long id){
        BookViewDto bookViewDto = service.find(id);
        List<PersonViewDto> all = personService.findAll();
        model.addAttribute("book",bookViewDto);
        model.addAttribute("people",all);
        return "book";
    }

    @PatchMapping("/{id}")
    public String assignPerson(@PathVariable("id") Long id, HttpServletRequest request){
        String personString = request.getParameter("person");
        if(personString == null || personString.isEmpty()){
            log.error("person id is empty or null");
            throw new IllegalArgumentException("person id is empty or null");
        }
        Long personId = Long.parseLong(personString);
        service.selectPerson(id,personId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String setAvailable(@PathVariable("id") Long id){
        service.setAvailable(id);
        return "redirect:/books";
    }
}
