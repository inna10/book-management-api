package com.inna.jpa_one2many.service;

import com.inna.jpa_one2many.dto.UserOrderSummaryDTO;
import com.inna.jpa_one2many.entity.User;
import com.inna.jpa_one2many.entity.Order;
import com.inna.jpa_one2many.repository.UserRepository;
import com.inna.jpa_one2many.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: UserOrderService class for managing user and order operations in the system.
 */
@Service
@Transactional
public class UserOrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves all users from the database.
     *
     * @return List of all users.
     */
    public List<User> getUsersWithMoreThanThreeOrders() {
        return userRepository.findUsersWithMoreThanThreeOrders();
    }

    /**
     * Retrieves the total order amount per user.
     *
     * @return List of UserOrderSummaryDTO containing user ID, name, and total order amount.
     */
    public List<UserOrderSummaryDTO> getTotalOrderAmountPerUser() {
        List<Object[]> results = userRepository.findTotalOrderAmountPerUser();
        return results.stream()
                .map(result -> new UserOrderSummaryDTO(
                        (Long) result[0],
                        (String) result[1],
                        result[2] == null ? BigDecimal.ZERO : (BigDecimal) result[2]))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves users who have no orders.
     *
     * @return List of users with no orders.
     */
    public List<User> getUsersWithNoOrders() {
        return userRepository.findUsersWithNoOrders();
    }

    /**
     * Retrieves all orders for a specific user.
     *
     * @param userId The ID of the user whose orders are to be retrieved.
     * @return List of orders for the specified user, ordered by date in descending order.
     */
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserIdOrderByDateDesc(userId);
    }
}

