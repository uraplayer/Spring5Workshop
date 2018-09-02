package com.tikalk.workshop.service;

import com.tikalk.workshop.entity.Person;
import com.tikalk.workshop.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sigals on 10/03/2018.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> storePersonsFlux(List<Person> persons) {

        return personRepository.saveAll(persons);
    }

    public Flux<Person> storePersonsMono(List<Person> persons) {
        return Flux.concat(
                persons
                .stream()
                .map(personRepository::save)
                .collect(Collectors.toList()));
    }
}
