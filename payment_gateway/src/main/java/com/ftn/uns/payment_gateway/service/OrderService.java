package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    PaymentDetailsService paymentDetailsService;

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order deleteOrder(Integer id) {
        orderRepository.deleteById(id);
        return null;
    }

    public Order createOrder(Order order) {

        if (order.getPrice().equals(null)) {
            return null;
        }

        if (order.getPayerId().equals(null)) {
            return null;
        }

        order.setMerchantTimestamp(LocalDateTime.now());
        order.setExecuted(null);

        order = orderRepository.save(order);

        return createOrderService(order);
    }

    public Order updateOrder(Integer id, Order order) {
        if (id.equals(null)) {
            return null;
        }

        Order newOrder = orderRepository.getOne(id);
        if (newOrder.equals(null)) {
            return null;
        }

        newOrder.setMerchantTimestamp(order.getMerchantTimestamp());

        if (order.getPrice().equals(null)) {
            return null;
        }
        newOrder.setPrice(order.getPrice());

        if (order.getPayerId().equals(null)) {
            return null;
        }
        newOrder.setPayerId(order.getPayerId());

        newOrder.setMagazine(order.getMagazine());

        return orderRepository.save(order);
    }

    private Order createOrderService(Order order){
        PaymentTypeGateway gateway = PaymentTypeGatewayFactory.getGateway(order.getType());
        return gateway.createOrder(order);
    }
}
