package SpringMVC.mapper;

import SpringMVC.dto.PersonCreateRequest;
import SpringMVC.dto.PersonDetailsView;
import SpringMVC.dto.PersonSummaryView;
import SpringMVC.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = BookMapper.class)
public interface PersonMapper  {
    PersonCreateRequest fromPersonToPersonDto(Person person);
    List<PersonCreateRequest> fromListPersonToPersonDto(List<Person> personList);

    @Mapping(target = "name",source = "name")
    @Mapping(target = "born",source = "born")
    Person fromPersonDtoToPerson(PersonCreateRequest personCreateRequest);


    PersonSummaryView fromPersonToPersonViewDto(Person person);
    @Mapping(target = "name",source = "name")
    @Mapping(target = "born",source = "born")
    Person fromPersonViewDtoToPerson(PersonSummaryView personSummaryView);
    List<PersonSummaryView> fromPersonListToPersonViewDtoList(List<Person> personList);


    PersonDetailsView personToPersonShowDto(Person person);
}
