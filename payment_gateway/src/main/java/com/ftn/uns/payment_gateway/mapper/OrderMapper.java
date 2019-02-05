package com.ftn.uns.payment_gateway.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.model.Bank;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.service.BankService;
import com.ftn.uns.payment_gateway.service.PaymentDetailsService;

@Service
public class OrderMapper {

    @Autowired
    MagazineRepository magazineRepository;
    
	@Autowired
	private BankService bankService;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;

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

        order.setMagazine(magazineRepository.findById(dto.getMagazinId()).get());
        order.setPayerId(dto.getPayerId());
        order.setPrice(dto.getAmount());
        order.setMerchantTimestamp(dto.getMerchantTimestamp());
        order.setMerchantOrderId(dto.getMerchantOrderId());
        order.setType(dto.getType());
        
        Bank bank = bankService.getBankByMerchantId(dto.getMerchantId());
        order.setBankUrl(bank.getBank_url());
        order.setMerchantPassword(paymentDetailsService.getMerchantPasswordByMerchantId(order.getMagazine().getIssn()));
        order.setMerchantId(order.getMagazine().getIssn()); // id prodavca

        return order;
    }

    public List<OrderDto> mapManyToDTO(List<Order> orders){
        return StreamSupport.stream(orders.spliterator(),false)
                .map((order) -> mapToDTO(order))
                .collect(Collectors.toList());
    }
}
