package SpringMVC.service;

import SpringMVC.dao.PersonDao;
import SpringMVC.dto.PersonDto;
import SpringMVC.entity.Person;
import SpringMVC.mapper.PersonMapper;
import SpringMVC.mapper.PersonMapperImpl;
import jakarta.transaction.Transactional;
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
    public List<PersonDto> findAll() {
        List<Person> all = personDao.findAll();
        return mapper.fromListPersonToPersonDto(all);
    }
}
