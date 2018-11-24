package tech.mohammad.amir.common.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.mohammad.amir.common.exception.InvalidFileException;

import java.net.URL;
import java.util.List;

public class JsonFileReader {
    public static List<Integer> read(final String fileName) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final URL fileResource = JsonFileReader.class.getClassLoader().getResource(fileName);

            return objectMapper.readerFor(List.class).readValue(fileResource);
        } catch (Exception e) {
            throw new InvalidFileException("Invalid File");
        }
    }
}
