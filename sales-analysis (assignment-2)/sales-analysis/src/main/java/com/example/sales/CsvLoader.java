package com.example.sales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to load sales records from a CSV resource on the classpath.
 */
public final class CsvLoader {

    private CsvLoader() {
    }

    public static List<SaleRecord> loadSalesFromResource(String resourcePath) {
        InputStream inputStream = CsvLoader.class.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // skip header line, map each line to SaleRecord using streams
            return reader.lines()
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(CsvLoader::parseLine)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV resource: " + resourcePath, e);
        }
    }

    private static SaleRecord parseLine(String line) {
        // CSV parsing, works because our sample data has no commas in fields
        String[] parts = line.split(",");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        String orderId = parts[0].trim();
        String region = parts[1].trim();
        String country = parts[2].trim();
        String category = parts[3].trim();
        String product = parts[4].trim();
        LocalDate orderDate = LocalDate.parse(parts[5].trim());
        int quantity = Integer.parseInt(parts[6].trim());
        double unitPrice = Double.parseDouble(parts[7].trim());

        return new SaleRecord(orderId, region, country, category, product, orderDate, quantity, unitPrice);
    }
}
