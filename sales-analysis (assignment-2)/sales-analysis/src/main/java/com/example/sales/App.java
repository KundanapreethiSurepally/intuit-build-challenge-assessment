package com.example.sales;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

/**
 * Main application that loads the CSV, runs analysis, and prints results to console.
 */
public class App {
    public static void main(String[] args) {
        // Load dataset from classpath
        List<SaleRecord> records = CsvLoader.loadSalesFromResource("/data/sales.csv");
        SalesAnalyzer analyzer = new SalesAnalyzer(records);

        System.out.println("=== Sales Data Analytics ===");
        System.out.println("Total records: " + records.size());

        // 1. Total revenue
        double totalRevenue = analyzer.getTotalRevenue();
        System.out.println("\n1) Total revenue: " + totalRevenue);

        // 2. Revenue by country
        System.out.println("\n2) Revenue by country:");
        Map<String, Double> revenueByCountry = analyzer.getRevenueByCountry();
        revenueByCountry.forEach((country, revenue) -> System.out.println("   " + country + ": " + revenue));

        // 3. Order count by category
        System.out.println("\n3) Order count by category:");
        Map<String, Long> ordersByCategory = analyzer.getOrderCountByCategory();
        ordersByCategory.forEach((category, count) -> System.out.println("   " + category + ": " + count + " orders"));

        // 4. Monthly revenue
        System.out.println("\n4) Monthly revenue:");
        Map<YearMonth, Double> monthlyRevenue = analyzer.getMonthlyRevenue();
        monthlyRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("   " + entry.getKey() + ": " + entry.getValue()));

        // 5. Top 3 products by revenue
        System.out.println("\n5) Top 3 products by revenue:");
        analyzer.getTopNProductsByRevenue(3)
                .forEach(entry -> System.out.println("   " + entry.getKey() + " -> " + entry.getValue()));

        // 6. Revenue by category
        System.out.println("\n6) Revenue by category:");
        analyzer.getRevenueByCategory()
                .forEach((category, revenue) -> System.out.println("   " + category + ": " + revenue));

        System.out.println("\nAnalysis completed.");
    }
}
