package com.inna.jpa_one2many.controller;

import com.inna.jpa_one2many.dto.UserOrderSummaryDTO;
import com.inna.jpa_one2many.entity.User;
import com.inna.jpa_one2many.entity.Order;
import com.inna.jpa_one2many.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/one2many-example")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/users/with-many-orders")
    public ResponseEntity<List<User>> getUsersWithMoreThanThreeOrders() {
        return ResponseEntity.ok(userOrderService.getUsersWithMoreThanThreeOrders());
    }

    @GetMapping("/users/order-summary")
    public ResponseEntity<List<UserOrderSummaryDTO>> getTotalOrderAmountPerUser() {
        return ResponseEntity.ok(userOrderService.getTotalOrderAmountPerUser());
    }

    @GetMapping("/users/without-orders")
    public ResponseEntity<List<User>> getUsersWithNoOrders() {
        return ResponseEntity.ok(userOrderService.getUsersWithNoOrders());
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(userOrderService.getUserOrders(userId));
    }

    // Adding Swagger documentation
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Get users with more than 3 orders",
        description = "Returns a list of users who have placed more than three orders"
    )
    @GetMapping("/stats/power-users")
    public ResponseEntity<List<User>> getPowerUsers() {
        return ResponseEntity.ok(userOrderService.getUsersWithMoreThanThreeOrders());
    }

    @io.swagger.v3.oas.annotations.Operation(
        summary = "Get order statistics",
        description = "Returns total order amounts for each user"
    )
    @GetMapping("/stats/order-totals")
    public ResponseEntity<List<UserOrderSummaryDTO>> getOrderStatistics() {
        return ResponseEntity.ok(userOrderService.getTotalOrderAmountPerUser());
    }
}


