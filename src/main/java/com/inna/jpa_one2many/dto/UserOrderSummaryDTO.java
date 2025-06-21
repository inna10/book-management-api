package com.inna.jpa_one2many.dto;

import java.math.BigDecimal;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: UserOrderSummaryDTO is a Data Transfer Object that summarizes the total order amount for a user.
 */
public class UserOrderSummaryDTO {
    private Long userId;
    private String userName;
    private BigDecimal totalAmount;

    public UserOrderSummaryDTO(Long userId, String userName, BigDecimal totalAmount) {
        this.userId = userId;
        this.userName = userName;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "UserOrderSummaryDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
