package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.controller.dto.PersonDto;
import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.domain.dto.BirthDay;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    //차단된 사람을 제외한 모든 사람을 불러오는 메소드
    public List<Person> getPeopleExcludeBlocks(){
        /*List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());*/
        return personRepository.findByBlockIsNull();
    }



    @Transactional(readOnly = true )
    public Person getPerson(Long id){
        // Person person = personRepository.findById(id).get();
        Person person =personRepository.findById(id).orElse(null);

        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
    /*    List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());*/
    return personRepository.findByName(name);
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        if(!person.getName().equals(personDto.getName())){
             throw new RuntimeException("이름이 다릅니다.");
        }

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        person.setName(name);

        personRepository.save(person);
    }
}
