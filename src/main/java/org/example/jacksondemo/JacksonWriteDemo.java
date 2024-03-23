package org.example.jacksondemo;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//? Writing a list of Java objects to a JSON file
public class JacksonWriteDemo {

    public static void main(String[] args) {

        try {
            // create a books list
            List<Book> books = Arrays.asList(
                    new Book("Thinking in Java", "978-0131872486", 1998,
                            new String[]{"Bruce Eckel"}),
                    new Book("Head First Java", "0596009208", 2003,
                            new String[]{"Kathy Sierra", "Bert Bates"})
            );

            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // create an instance of DefaultPrettyPrinter
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            // convert book object to JSON file
            //mapper.writeValue(Paths.get("data/book1.json").toFile(), books);
            writer.writeValue(Paths.get("data/book2.json").toFile(), books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
