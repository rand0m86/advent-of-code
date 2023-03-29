package org.example.y2022.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InputReadUtil {
    public static List<String> readAllLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public static List<String> readAllLines(int day, String file) throws IOException {
        return Files.readAllLines(Paths.get("src/main/resources/org/example/y2022", "day" + day, file));
    }
}
