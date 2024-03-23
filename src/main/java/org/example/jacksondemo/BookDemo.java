package org.example.jacksondemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;

public class BookDemo {

    public static void main(String[] args) {
        try {
            // create book object
            Book book = new Book("Thinking in Java", "978-0131872486", 1998,
                    new String[]{"Bruce Eckel"});

            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert book object to JSON file
            mapper.writeValue(Paths.get("data/book.json").toFile(), book);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
