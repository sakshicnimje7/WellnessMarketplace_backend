package com.infosys.wmat.repository;

import com.infosys.wmat.entity.Order;
import com.infosys.wmat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // THIS METHOD WAS MISSING causing the error in OrderController
    List<Order> findByUser(User user);
}