package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Orders;
import com.labprog.labprog.model.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Orders save() {
        return null;
    }

}
