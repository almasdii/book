package SpringMVC.controller;

import SpringMVC.dto.PersonCreateRequest;
import SpringMVC.dto.PersonDetailsView;
import SpringMVC.dto.PersonSummaryView;
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
        List<PersonSummaryView> all = personService.findAll();
        model.addAttribute("people", all);
        return "people";
    }


    @GetMapping("/new")
    public String newPeoplePage(@ModelAttribute("person") PersonCreateRequest personCreateRequest) {
        return "newPerson";
    }


    @PostMapping
    public String newPeople(@ModelAttribute("person") PersonCreateRequest personCreateRequest) {
        personService.save(personCreateRequest);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String personPage(Model model, @PathVariable("id") Long id) {
        PersonDetailsView personDetailsView= personService.find(id);
        model.addAttribute("person", personDetailsView);
        return "person";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(Model model,@PathVariable("id") Long id){
        PersonDetailsView personDetailsView = personService.find(id);
        log.debug("Person in edit : {} " , personDetailsView);
        model.addAttribute("person",personDetailsView);
        return "personUpdate";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") PersonSummaryView personSummaryView, @PathVariable("id") Long id){
        personService.update(id, personSummaryView);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        personService.delete(id);
        return "redirect:/people";
    }
}

