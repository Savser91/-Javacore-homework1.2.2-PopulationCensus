package com.company;

import com.company.enumType.*;
import com.company.person.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<String> listOfFamilies = new ArrayList<>();
        List<Person> listOfWorkers = new ArrayList<>();
        Collection<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10_000_000; i++) {
            ((ArrayList<Person>) persons).add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        olderThan18(persons);
        listOfFamilies = perspectiveMilitary(persons);
        listOfWorkers = findWorkers(persons);
    }

    public static void olderThan18 (Collection<Person> persons) {
        System.out.println("Количество людей старше 18 лет - " + persons.stream().filter(x -> x.getAge() > 18).count());
    }

    public static List<String> perspectiveMilitary (Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
    }

    public static List<Person> findWorkers (Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER && x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.MAN ? x.getAge() <= 65 : x.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
