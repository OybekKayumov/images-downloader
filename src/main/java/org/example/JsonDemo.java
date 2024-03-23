package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDemo {

    public static void main(String[] args) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFile = Files.readString(Paths.get("data/map.json"));
        JsonNode jsonData = objectMapper.readTree(jsonFile);
        JsonNode stations = jsonData.get("stations");
        JsonNode lines = jsonData.get("lines");

        for (JsonNode line : lines) {
            ObjectNode lineNode = (ObjectNode) line;
            lineNode.remove("color");
            String lineNumber = line.get("number").asText();
            JsonNode stationList = stations.get(lineNumber);
            int stationCount = stationList.size();
            lineNode.put("stationCount", stationCount);
        }

        ObjectMapper mapper = new ObjectMapper();
        File output = new File("data/export.json");
        mapper.writeValue(output, lines);
    }
}
