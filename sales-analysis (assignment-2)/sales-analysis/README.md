# Sales Analysis – Java Streams Assignment

## Overview

This project demonstrates data analysis using **Java Streams, lambdas, and functional-style aggregation** on CSV sales data.

It:
- Loads data from `src/main/resources/data/sales.csv`
- Uses `Stream` operations (`map`, `filter`, `groupingBy`, `summingDouble`, etc.)
- Performs multiple analytical queries
- Prints results to the console
- Includes JUnit5 tests for all analysis methods

## Tech Stack

- Java 11+
- Maven
- JUnit 5

## Project Structure

- `SaleRecord` – domain model representing a row in the CSV
- `CsvLoader` – loads and parses CSV into `List<SaleRecord>`
- `SalesAnalyzer` – performs all analytics using Streams
- `App` – main entry point
- `SalesAnalyzerTest` – unit tests covering analysis logic

## Running the Application

From the project root (where `pom.xml` is):

```bash
mvn compile exec:java -Dexec.mainClass="com.example.sales.App"
```

## Sample Output

=== Sales Data Analytics ===
Total records: 15

1) Total revenue: 892400.0

2) Revenue by country:
   India: ...
   USA: ...
   Germany: ...
   Japan: ...
   France: ...

3) Order count by category:
   Electronics: ...
   Furniture: ...

4) Monthly revenue:
   2024-01: ...
   2024-02: ...
   2024-03: ...
   2024-04: ...

5) Top 3 products by revenue:
   Laptop Pro -> ...
   Smartphone X -> ...
   Tablet Plus -> ...

6) Revenue by category:
   Electronics: ...
   Furniture: ...

Analysis completed.
