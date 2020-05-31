package com.kelelas.germes.service;

import com.kelelas.germes.config.ConstantBundle;
import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.entity.*;
import com.kelelas.germes.exception.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPageService {
    UserService userService;
    OrderService orderService;
    HistoryService historyService;
    CartService cartService;
    BillService billService;
    int sum= ConstantBundle.getIntProperty("number.defaultNumber");

    @Autowired
    public UserPageService(UserService userService, OrderService orderService, HistoryService historyService, CartService cartService, BillService billService) {
        this.userService = userService;
        this.orderService = orderService;
        this.historyService = historyService;
        this.cartService = cartService;
        this.billService = billService;

    }


    @Transactional
    public void payForOrderById(String id) {
        try {

            Bill bill = billService.getBillById(Long.valueOf(id));
            if (bill.getStatusId() == ConstantBundle.getIntProperty("status.waitingForPay")) {
                pay(bill.getPrice());
                bill.setStatusId((long) ConstantBundle.getIntProperty("status.finished"));
                billService.update(bill);
                historyService.save(convertBillToHistory(bill));
            }

        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void refillUserBalance(){
        user().setBalance(user().getBalance() + ConstantBundle.getIntProperty("balance.addMoneyToUserBalance"));
        userService.save(user());
    }





    public void addOrderToCart( Long id) {
        cartService.addToCart(
                Cart.builder()
                .userId(user().getId())
                .dishId(id)
                .build());
    }

    public void deleteOrderFromCart(Long id){
        cartService.deleteFromCart(
                Cart.builder()
                        .userId(user().getId())
                        .dishId(id)
                        .build());
        sum();
    }

    public List<Order> orders()  {
        return cartService.getCart(user().getId());
    }

    public List<OrderDTO> localOrders() {
        return cartService.getLocalCart(user().getId());
    }

    public int sum() {
        sum = ConstantBundle.getIntProperty("number.defaultNumber");
        for (Order order : orders()){
            sum += order.getPrice();
        }
        return sum;
    }

    public void confirm(){
        if (user().getBalance()>=sum() ) {
            saveToStory();
            cartService.clearCart(user().getId());
        }
    }


    public void pay(int sum){
        user().setBalance(user().getBalance() - sum);
        userService.save(user());
    }

    public void saveToStory() {
        History historyItem = History.builder()
                .date(LocalDateTime.now())
                .price(sum())
                .userId(user().getId())
                .statusId((long) ConstantBundle.getIntProperty("status.waitingForConfirm"))
                .orders(orders())
                .build();
        historyService.save(historyItem);
    }

    public History convertBillToHistory(Bill bill){
        return History.builder()
                .statusId(bill.getStatusId())
                .orders(bill.getOrders())
                .date(bill.getDate())
                .price(bill.getPrice())
                .userId(bill.getUserId())
                .id(bill.getId())
                .build();
    }

    public User user(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
