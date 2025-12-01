package com.example.sales;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SalesAnalyzerTest {

    private List<SaleRecord> createSampleRecords() {
        return List.of(
                new SaleRecord("1", "APAC", "India", "Electronics", "Phone A",
                        LocalDate.of(2024, 1, 10), 2, 10000),
                new SaleRecord("2", "APAC", "India", "Electronics", "Phone B",
                        LocalDate.of(2024, 1, 15), 1, 15000),
                new SaleRecord("3", "EU", "Germany", "Furniture", "Chair",
                        LocalDate.of(2024, 2, 1), 3, 5000),
                new SaleRecord("4", "NA", "USA", "Electronics", "Phone A",
                        LocalDate.of(2024, 2, 20), 1, 12000));
    }

    @Test
    public void testTotalRevenue() {
        SalesAnalyzer analyzer = new SalesAnalyzer(createSampleRecords());
        double total = analyzer.getTotalRevenue();
        // (2 * 10000) + (1 * 15000) + (3 * 5000) + (1 * 12000)
        assertEquals(20000 + 15000 + 15000 + 12000, total);
    }

    @Test
    public void testRevenueByCountry() {
        SalesAnalyzer analyzer = new SalesAnalyzer(createSampleRecords());
        Map<String, Double> revenueByCountry = analyzer.getRevenueByCountry();

        assertEquals(35000.0, revenueByCountry.get("India")); // 20000 + 15000
        assertEquals(15000.0, revenueByCountry.get("Germany")); // 3 * 5000
        assertEquals(12000.0, revenueByCountry.get("USA")); // 1 * 12000
    }

    @Test
    public void testOrderCountByCategory() {
        SalesAnalyzer analyzer = new SalesAnalyzer(createSampleRecords());
        Map<String, Long> countByCategory = analyzer.getOrderCountByCategory();

        assertEquals(3L, countByCategory.get("Electronics")); // records 1,2,4
        assertEquals(1L, countByCategory.get("Furniture")); // record 3
    }

    @Test
    public void testMonthlyRevenue() {
        SalesAnalyzer analyzer = new SalesAnalyzer(createSampleRecords());
        Map<YearMonth, Double> monthlyRevenue = analyzer.getMonthlyRevenue();

        YearMonth jan = YearMonth.of(2024, 1);
        YearMonth feb = YearMonth.of(2024, 2);

        assertEquals(35000.0, monthlyRevenue.get(jan)); // records 1 & 2
        assertEquals(27000.0, monthlyRevenue.get(feb)); // 15000 + 12000
    }

    @Test
    public void testTopNProductsByRevenue() {
        SalesAnalyzer analyzer = new SalesAnalyzer(createSampleRecords());
        var top2 = analyzer.getTopNProductsByRevenue(2);

        // Revenue: Phone A = 2*10000 + 1*12000 = 32000
        // Phone B = 15000
        // Chair = 15000
        assertEquals("Phone A", top2.get(0).getKey());
        assertEquals(32000.0, top2.get(0).getValue());
        assertEquals(2, top2.size());
    }
}
