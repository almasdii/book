package SpringMVC.service;

import SpringMVC.dao.PersonDao;
import SpringMVC.dto.PersonCreateRequest;
import SpringMVC.dto.PersonDetailsView;
import SpringMVC.dto.PersonSummaryView;
import SpringMVC.entity.Person;
import SpringMVC.exception.PersonNotFoundException;
import SpringMVC.mapper.PersonMapper;
import SpringMVC.mapper.PersonMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDao personDao;
    private final PersonMapper mapper;

    @Autowired
    public PersonService(PersonDao personDao, PersonMapperImpl mapper) {
        this.personDao = personDao;
        this.mapper = mapper;
    }
    public List<PersonSummaryView> findAll() {
        List<Person> all = personDao.findAll();
        return mapper.fromPersonListToPersonViewDtoList(all);
    }

    public void save(PersonCreateRequest personCreateRequest) {
        Person person = mapper.fromPersonDtoToPerson(personCreateRequest);
        personDao.save(person);
    }

    public PersonDetailsView find(Long id) {
        Person person = personDao.find(id)
                .orElseThrow(() -> new PersonNotFoundException("Person Doesnt exist with this id"));
        return mapper.personToPersonShowDto(person);

    }

    public void update(Long id, PersonSummaryView personSummaryView) {
        Person person = mapper.fromPersonViewDtoToPerson(personSummaryView);
        personDao.update(id,person);
    }

    public void delete(Long id) {
        personDao.delete(id);
    }

}
