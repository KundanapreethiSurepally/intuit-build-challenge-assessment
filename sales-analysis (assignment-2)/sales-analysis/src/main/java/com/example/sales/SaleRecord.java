package com.example.sales;

import java.time.LocalDate;

/**
 * Represents a single sales record (one row in the CSV).
 */
public class SaleRecord {
    private final String orderId;
    private final String region;
    private final String country;
    private final String category;
    private final String product;
    private final LocalDate orderDate;
    private final int quantity;
    private final double unitPrice;

    public SaleRecord(
            String orderId,
            String region,
            String country,
            String category,
            String product,
            LocalDate orderDate,
            int quantity,
            double unitPrice) {
        this.orderId = orderId;
        this.region = region;
        this.country = country;
        this.category = category;
        this.product = product;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "SaleRecord{" +
                "orderId='" + orderId + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", product='" + product + '\'' +
                ", orderDate=" + orderDate +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
