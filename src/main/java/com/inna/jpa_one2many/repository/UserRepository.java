package com.inna.jpa_one2many.repository;

import com.inna.jpa_one2many.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find users with more than 3 orders
    @Query("SELECT u FROM User u WHERE SIZE(u.orders) > 3")
    List<User> findUsersWithMoreThanThreeOrders();

    // Find total order amount per user
    @Query("SELECT u.id, u.name, SUM(o.amount) as totalAmount " +
            "FROM User u LEFT JOIN u.orders o " +
            "GROUP BY u.id, u.name")
    List<Object[]> findTotalOrderAmountPerUser();

    // Find users with no orders
    @Query("SELECT u FROM User u WHERE u.orders IS EMPTY")
    List<User> findUsersWithNoOrders();
}

