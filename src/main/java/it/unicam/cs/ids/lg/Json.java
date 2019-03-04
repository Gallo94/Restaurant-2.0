package it.unicam.cs.ids.lg;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    private static final String dataName = "data";
    private static final String extension = "json";

    private File data;

    public Json() throws IOException {
        data = new File(dataName + "." + extension);
        data.createNewFile();
    }

    public void saveTables(final TableManager manager) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(data, manager);
    }

    public TableManager loadTables() throws JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, TableManager.class);
        } catch (IOException e) {
            System.out.println("data.json: No file found or file empty");
        }

        return new TableManager();
    }
}