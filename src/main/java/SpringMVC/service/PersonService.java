package SpringMVC.service;

import SpringMVC.dao.PersonDao;
import SpringMVC.dto.PersonDto;
import SpringMVC.dto.PersonViewDto;
import SpringMVC.entity.Person;
import SpringMVC.exception.PersonNotFoundException;
import SpringMVC.mapper.PersonMapper;
import SpringMVC.mapper.PersonMapperImpl;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
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
    public List<PersonViewDto> findAll() {
        List<Person> all = personDao.findAll();
        return mapper.fromPersonListToPersonViewDtoList(all);
    }

    public void save(PersonDto personDto) {
        Person person = mapper.fromPersonDtoToPerson(personDto);
        personDao.save(person);
    }

    public PersonViewDto find(Long id) {
        Person person = personDao.find(id)
                .orElseThrow(() -> new PersonNotFoundException("Person Doesnt exist with this id"));
        return mapper.fromPersonToPersonViewDto(person);

    }

    public void update(Long id, PersonViewDto personViewDto) {
        Person person = mapper.fromPersonViewDtoToPerson(personViewDto);
        personDao.update(id,person);
    }

    public void delete(Long id) {
        personDao.delete(id);
    }
}
