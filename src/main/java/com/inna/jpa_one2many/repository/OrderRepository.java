package com.inna.jpa_one2many.repository;

import com.inna.jpa_one2many.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders for a specific user
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);

    // Find orders by user ordered by date descending
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.orderDate DESC")
    List<Order> findOrdersByUserIdOrderByDateDesc(@Param("userId") Long userId);
}
