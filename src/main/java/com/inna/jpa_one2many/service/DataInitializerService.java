package com.inna.jpa_one2many.service;

import com.inna.jpa_one2many.entity.User;
import com.inna.jpa_one2many.entity.Order;
import com.inna.jpa_one2many.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: DataInitializerService initializes the database with sample data for testing purposes.
 */
@Component
public class DataInitializerService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Initializes the database with sample data.
     */
    @PostConstruct
    @Transactional
    public void initializeData() {
        // User with no orders
        User user1 = new User("Sarah Cohen", "sarahc@example.com");
        userRepository.save(user1);

        // User with 2 orders
        User user2 = new User("Avram Levi", "avraml@example.com");
        user2.addOrder(new Order("ORD-1", new BigDecimal("100.00")));
        user2.addOrder(new Order("ORD-2", new BigDecimal("150.00")));
        userRepository.save(user2);

        // User with 4 orders (for testing more than 3 orders query)
        User user3 = new User("David Rabin", "davidr@example.com");
        user3.addOrder(new Order("ORD-3", new BigDecimal("200.00")));
        user3.addOrder(new Order("ORD-4", new BigDecimal("300.00")));
        user3.addOrder(new Order("ORD-5", new BigDecimal("400.00")));
        user3.addOrder(new Order("ORD-6", new BigDecimal("500.00")));
        userRepository.save(user3);

        // Another user with 5 orders
        User user4 = new User("Rachel Katz", "rachelk@example.com");
        user4.addOrder(new Order("ORD-7", new BigDecimal("250.00")));
        user4.addOrder(new Order("ORD-8", new BigDecimal("350.00")));
        user4.addOrder(new Order("ORD-9", new BigDecimal("450.00")));
        user4.addOrder(new Order("ORD-10", new BigDecimal("550.00")));
        user4.addOrder(new Order("ORD-11", new BigDecimal("650.00")));
        userRepository.save(user4);

        // Another user with no orders
        User user5 = new User("Ruth Epstein", "Ruthe@example.com");
        userRepository.save(user5);
    }
}
