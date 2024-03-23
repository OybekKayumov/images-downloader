package org.example.jacksondemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JacksonD {

    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("blue", "jiguly");
        objectMapper.writeValue(new File("data/car.json"), car);

        String carAsString = objectMapper.writeValueAsString(car);
        System.out.println(carAsString);
        //{"color":"blue","type":"jiguly"}


    }
}
