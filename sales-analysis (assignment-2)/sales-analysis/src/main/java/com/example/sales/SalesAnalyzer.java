package com.example.sales;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Performs analytical operations on a list of SaleRecord objects using Java Streams.
 */
public class SalesAnalyzer {

    private final List<SaleRecord> records;

    public SalesAnalyzer(List<SaleRecord> records) {
        this.records = List.copyOf(records);
    }

    /** Total revenue (sum of quantity * unitPrice across all records). */
    public double getTotalRevenue() {
        return records.stream()
                .mapToDouble(SaleRecord::getTotalPrice)
                .sum();
    }

    /** Revenue by country. */
    public Map<String, Double> getRevenueByCountry() {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getCountry,
                        Collectors.summingDouble(SaleRecord::getTotalPrice)));
    }

    /** Number of orders by category. */
    public Map<String, Long> getOrderCountByCategory() {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getCategory,
                        Collectors.counting()));
    }

    /** Monthly revenue (grouped by YearMonth). */
    public Map<YearMonth, Double> getMonthlyRevenue() {
        return records.stream()
                .collect(Collectors.groupingBy(
                        r -> YearMonth.from(r.getOrderDate()),
                        Collectors.summingDouble(SaleRecord::getTotalPrice)));
    }

    /** Revenue by product, sorted descending, top N products. */
    public List<Map.Entry<String, Double>> getTopNProductsByRevenue(int n) {
        Map<String, Double> revenueByProduct = records.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getProduct,
                        Collectors.summingDouble(SaleRecord::getTotalPrice)));

        return revenueByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    /** Revenue by category */
    public Map<String, Double> getRevenueByCategory() {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getCategory,
                        Collectors.summingDouble(SaleRecord::getTotalPrice)));
    }
}
