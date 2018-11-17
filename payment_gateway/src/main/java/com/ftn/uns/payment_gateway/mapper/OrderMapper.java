package com.ftn.uns.payment_gateway.mapper;

import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    @Autowired
    MagazineRepository magazineRepository;

    public OrderDto mapToDTO(Order order){
        OrderDto dto = new OrderDto();

        dto.setAmount(order.getPrice());
        dto.setMerchantId(order.getMagazine().getIssn());
        dto.setMerchantOrderId(order.getMerchantOrderId());
        dto.setMerchantTimestamp(order.getMerchantTimestamp());
        dto.setPayerId(order.getPayerId());
        dto.setType(order.getType());

        return dto;
    }

    public Order mapFromDTO(OrderDto dto) {
        Order order = new Order();

        order.setMagazine(magazineRepository.getOne(dto.getMerchantId()));
        order.setPayerId(dto.getPayerId());
        order.setPrice(dto.getAmount());
        order.setMerchantTimestamp(dto.getMerchantTimestamp());
        order.setMerchantOrderId(dto.getMerchantOrderId());
        order.setType(dto.getType());

        return order;
    }
}
