package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public Order findById(Integer id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return transactionRepository.findAll();
    }

    public Order deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
        return null;
    }

    public Order createTransaction(Order order) {
        return null;
    }

    public Order updateTransaction(Integer id, Order order) {
        return null;
    }

}
