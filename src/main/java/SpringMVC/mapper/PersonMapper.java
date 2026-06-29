package SpringMVC.mapper;

import SpringMVC.dto.PersonDto;
import SpringMVC.dto.PersonViewDto;
import SpringMVC.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = BookMapper.class)
public interface PersonMapper  {
    PersonDto fromPersonToPersonDto(Person person);
    List<PersonDto> fromListPersonToPersonDto(List<Person> personList);

    @Mapping(target = "name",source = "name")
    @Mapping(target = "born",source = "born")
    Person fromPersonDtoToPerson(PersonDto personDto);


    PersonViewDto fromPersonToPersonViewDto(Person person);
    @Mapping(target = "name",source = "name")
    @Mapping(target = "born",source = "born")
    Person fromPersonViewDtoToPerson(PersonViewDto personViewDto);
    List<PersonViewDto> fromPersonListToPersonViewDtoList(List<Person> personList);
}
