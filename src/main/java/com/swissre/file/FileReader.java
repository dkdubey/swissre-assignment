package com.swissre.file;

import com.swissre.exception.PortfolioException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileReader {

    private final String delimiter = "=";

    public Map<String, Integer> readFile(String file) {

        if (file == null || !new File(file).exists()) {
            throw new PortfolioException("File doesn't exist");
        }

        Map<String, Integer> map = new HashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            lines.filter(line -> line.contains(delimiter)).forEach(
                    line -> map.putIfAbsent(line.split(delimiter)[0], Integer.parseInt(line.split(delimiter)[1]))
            );
        } catch (IOException e) {
            throw new PortfolioException("Couldn't read the crypto file.", e);
        }
        return map;
    }


}
