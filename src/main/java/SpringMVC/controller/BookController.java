package SpringMVC.controller;

import SpringMVC.dto.BookDetailsView;
import SpringMVC.dto.BookSummaryView;
import SpringMVC.dto.PersonSummaryView;
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
    private final PersonService personService;

    @Autowired
    public BookController(BookService service, PersonService personService) {
        this.service = service;
        this.personService = personService;
    }

    @GetMapping
    public String booksPage(Model model){
        List<BookSummaryView> all = service.findAll();
        model.addAttribute("books",all);
        return "books";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") BookSummaryView bookSummaryView){
        return "newBook";
    }

    @PostMapping
    public String newBook(@ModelAttribute("book") BookSummaryView bookSummaryView){
        service.save(bookSummaryView);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookPage(Model model, @PathVariable("id") Long id){
        BookDetailsView bookDetailsView = service.find(id);
        List<PersonSummaryView> all = personService.findAll();
        model.addAttribute("book", bookDetailsView);
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
