package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CSVStreamProcessor {
    public static double calculateAverageAge(String csvFilePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(csvFilePath))) {
            return lines.skip(1)
                    .map(line -> {
                        String[] parts = line.split(",");
                        if (parts.length < 2) return null;
                        try {
                            return Integer.parseInt(parts[1]);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(age -> age != null)
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\LENOVO\\CSVProjectDone\\src\\main\\java\\org\\example\\people.csv";
        try {
            double averageAge = calculateAverageAge(filePath);
            System.out.printf("Average Age: "+ averageAge);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

