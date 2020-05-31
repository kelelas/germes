package com.kelelas.germes.service;

import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.entity.Order;
import com.kelelas.germes.mapper.LocaleOrderMapper;
import com.kelelas.germes.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    OrderRepository orderRepository;
    LocaleOrderMapper mapper;
    @Autowired
    public OrderService(OrderRepository orderRepository, LocaleOrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }


    public Page<OrderDTO> getLocaleOrders( Pageable pageable){
            return orderRepository.findAll(pageable).map(mapper :: dtoMapper);
    }
    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Page<OrderDTO> getLocalOrdersByGroup(int groupId, Pageable pageable){
        return orderRepository.findAllByGroupId(groupId, pageable).map(mapper::dtoMapper);
    }
}
