package SpringMVC.controller;

import SpringMVC.dto.PersonDto;
import SpringMVC.dto.PersonViewDto;
import SpringMVC.entity.Person;
import SpringMVC.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String peoplePage(Model model) {
        List<PersonViewDto> all = personService.findAll();
        model.addAttribute("people", all);
        return "people";
    }


    @GetMapping("/new")
    public String newPeoplePage(@ModelAttribute("person") PersonDto personDto) {
        return "newPerson";
    }


    @PostMapping
    public String newPeople(@ModelAttribute("person") PersonDto personDto) {
        personService.save(personDto);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String personPage(Model model, @PathVariable("id") Long id) {
        PersonViewDto personViewDto = personService.find(id);
        model.addAttribute("person", personViewDto);
        return "person";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(Model model,@PathVariable("id") Long id){
        PersonViewDto personViewDto = personService.find(id);
        log.debug("Person in edit : {} " , personViewDto);
        model.addAttribute("person",personViewDto);
        return "personUpdate";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") PersonViewDto personViewDto,@PathVariable("id") Long id){
        personService.update(id,personViewDto);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        personService.delete(id);
        return "redirect:/people";
    }
}

