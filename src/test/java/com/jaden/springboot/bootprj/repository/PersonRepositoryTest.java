package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("jaden");
        person.setAge(10);
        person.setAddress("bundang");

        personRepository.save(person);

        System.out.println(personRepository.findAll());
        List<Person> people = personRepository.findAll();

        assertThat(people.stream().count()).isEqualTo(1);
        assertThat(people.stream().anyMatch(p -> p.getName().equals("jaden"))).isEqualTo(true);
        assertThat(people.stream().anyMatch(p -> p.getAge()==10)).isEqualTo(true);
        assertThat(people.stream().anyMatch(p -> p.getAddress().equals("bundang"))).isEqualTo(true);
    }

    @Test
   void hashCodeAndEquals(){
        Person person1 = new Person("jaden", 10,"A");
        Person person2 = new Person("jaden", 10,"A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
   }

}