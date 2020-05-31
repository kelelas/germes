package com.kelelas.germes.service;

import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.entity.Cart;
import com.kelelas.germes.entity.Order;
import com.kelelas.germes.mapper.LocaleOrderMapper;
import com.kelelas.germes.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartService {
    CartRepository cartRepository;
    LocaleOrderMapper mapper;
@Autowired
    public CartService(CartRepository cartRepository, LocaleOrderMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public void addToCart(Cart cart){
        cartRepository.save(cart);
    }

    public void deleteFromCart(Cart cart){
        cartRepository.deleteDishFromUserCart(cart.getUserId(), cart.getDishId());
    }

    public List<OrderDTO> getLocalCart(Long userId){
        return cartRepository.findAllByUserId(userId).stream().map(Cart::getOrder).map(mapper::dtoMapper).collect(Collectors.toList());
}

    public List<Order> getCart(Long userId){
        return cartRepository.findAllByUserId(userId).stream().map(Cart::getOrder).collect(Collectors.toList());
    }

    public void clearCart(Long userId){
        cartRepository.deleteFullUserCart(userId);
    }
}
