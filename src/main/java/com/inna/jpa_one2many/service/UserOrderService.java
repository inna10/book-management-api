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

@Service
@Transactional
public class UserOrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<User> getUsersWithMoreThanThreeOrders() {
        return userRepository.findUsersWithMoreThanThreeOrders();
    }

    public List<UserOrderSummaryDTO> getTotalOrderAmountPerUser() {
        List<Object[]> results = userRepository.findTotalOrderAmountPerUser();
        return results.stream()
                .map(result -> new UserOrderSummaryDTO(
                        (Long) result[0],
                        (String) result[1],
                        result[2] == null ? BigDecimal.ZERO : (BigDecimal) result[2]))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithNoOrders() {
        return userRepository.findUsersWithNoOrders();
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserIdOrderByDateDesc(userId);
    }
}

