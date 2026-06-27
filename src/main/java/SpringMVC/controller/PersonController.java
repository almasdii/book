package SpringMVC.controller;

import SpringMVC.dto.PersonDto;
import SpringMVC.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String peoplePage(Model model){
        List<PersonDto> all = personService.findAll();
        model.addAttribute("people",all);
        return "people";
    }

    @GetMapping("/new")
    public String newPeoplePage(){

        re
    }


}
