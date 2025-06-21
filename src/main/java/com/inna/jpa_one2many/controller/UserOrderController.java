package com.inna.jpa_one2many.controller;

import com.inna.jpa_one2many.dto.UserOrderSummaryDTO;
import com.inna.jpa_one2many.entity.User;
import com.inna.jpa_one2many.entity.Order;
import com.inna.jpa_one2many.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: UserOrderController handles requests related to users and their orders in a one-to-many relationship.
 */
@RestController
@RequestMapping("/one2many-example")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    /**
     * Endpoint to get all users with more than three orders.
     * @return List of users with more than three orders
     */
    @GetMapping("/users/with-many-orders")
    public ResponseEntity<List<User>> getUsersWithMoreThanThreeOrders() {
        return ResponseEntity.ok(userOrderService.getUsersWithMoreThanThreeOrders());
    }

    /**
     * Endpoint to get the total order amount per user.
     * @return List of UserOrderSummaryDTO containing user ID and total order amount
     */
    @GetMapping("/users/order-summary")
    public ResponseEntity<List<UserOrderSummaryDTO>> getTotalOrderAmountPerUser() {
        return ResponseEntity.ok(userOrderService.getTotalOrderAmountPerUser());
    }

    /**
     * Endpoint to get users who have no orders.
     * @return List of users with no orders
     */
    @GetMapping("/users/without-orders")
    public ResponseEntity<List<User>> getUsersWithNoOrders() {
        return ResponseEntity.ok(userOrderService.getUsersWithNoOrders());
    }

    /**
     * Endpoint to get all orders for a specific user.
     * @param userId The ID of the user whose orders are to be retrieved
     * @return List of orders for the specified user
     */
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(userOrderService.getUserOrders(userId));
    }

    // Adding Swagger documentation
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Get users with more than 3 orders",
            description = "Returns a list of users who have placed more than three orders"
    )

    /**
     * Endpoint to get users with more than three orders.
     * @return List of users with more than three orders
     */
    @GetMapping("/stats/power-users")
    public ResponseEntity<List<User>> getPowerUsers() {
        return ResponseEntity.ok(userOrderService.getUsersWithMoreThanThreeOrders());
    }

    // Adding Swagger documentation
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Get order statistics",
            description = "Returns total order amounts for each user"
    )

    /**
     * Endpoint to get order statistics, including total order amounts per user.
     * @return List of UserOrderSummaryDTO containing user ID and total order amount
     */
    @GetMapping("/stats/order-totals")
    public ResponseEntity<List<UserOrderSummaryDTO>> getOrderStatistics() {
        return ResponseEntity.ok(userOrderService.getTotalOrderAmountPerUser());
    }
}


