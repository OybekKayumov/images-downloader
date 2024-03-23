package org.example.jacksondemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JacksonD {

    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("blue", "jiguly");
        objectMapper.writeValue(new File("data/car.json"), car);

        String carAsString = objectMapper.writeValueAsString(car);
        System.out.println(carAsString);
        //{"color":"blue","type":"jiguly"}

        //* converting a JSON String to a Java object
//        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
//        Car car1 = objectMapper.readValue(json, Car.class);
//        Car car1 = objectMapper.readValue(new File("data/json_car.json"),
//                Car.class);

        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();
        System.out.println(color);
        // Output: color -> Black

        //? Creating a Java List From a JSON Array String
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});

        //? Creating Java Map From JSON String
        String json1 = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map
                = objectMapper.readValue(json1, new TypeReference<Map<String,
                Object>>(){});


    }
}
