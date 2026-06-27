package SpringMVC.mapper;

import SpringMVC.dto.PersonDto;
import SpringMVC.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper  {
    PersonDto fromPersonToPersonDto(Person person);
    List<PersonDto> fromListPersonToPersonDto(List<Person> personList);
}
