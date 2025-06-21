package com.inna.jpa_one2many.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: Order entity representing a customer's order in the system.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// unique identifier for the order in DB

    private String orderNumber; // customer facing order number
    private BigDecimal amount;
    private LocalDateTime orderDate;

    @JsonBackReference   // prevents infinite recursion during serialization, having bidirectional relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // bidirectional relationship, also just userId may be used as altrnative

    // Constructors
    public Order() {
    }

    public Order(String orderNumber, BigDecimal amount) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
