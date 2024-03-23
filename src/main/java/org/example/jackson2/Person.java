package org.example.jackson2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.Getter;
import org.example.jacksondemo.Car;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Data
public class Person {

    private String name;
    private int age;
    private boolean isMarried;

    private List<String> hobbies;
    private List<String> children;

    private Car1 car;
    private Job job;

    public static void main(String[] args) throws Exception{
        String json = Files.readString(Paths.get("data/person.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Person person = objectMapper.readValue(json, Person.class);

        System.out.println("Name: " + person.getName());
        System.out.println(person.toString());

        //? Запись файла JSON в Java
        person.setChildren(List.of("Olga", "Petr"));

        Car1 car1 = new Car1();
        car1.setLicensePlate("A123BB765");
        car1.setModel("жигули");
        car1.setColor("кук");
        person.setCar(car1);

        String newJsom = objectMapper.writeValueAsString(person);

        FileWriter fileWriter = new FileWriter("data/personModified.json");
        fileWriter.write(newJsom);
        fileWriter.close();
    }
}

@Data
class Job {
    private LocalDate since;
    private String city;
    private BigDecimal salary;

}

@Data
class Car1 {
    private String licensePlate;
    private String model;
    private String color;

}

//Сериализация — процесс преобразования объекта Java в другой формат
// (например, JSON или бинарный формат) для его передачи или сохранения в файле.
// При сериализации объекта его состояние и данные преобразуются в формат,
// который может быть сохранён или передан на другую машину или в приложение.
//
// Десериализация — процесс обратного преобразования данных из ранее
// сериализованных объектов (JSON или бинарного формата) в объект Java.
// При десериализации данные восстанавливаются в исходное состояние
// и объект Java может быть использован в приложении.