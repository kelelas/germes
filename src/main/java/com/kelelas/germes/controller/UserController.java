package com.kelelas.germes.controller;

import com.kelelas.germes.config.ConstantBundle;
import com.kelelas.germes.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    UserService userService;
    OrderService orderService;
    HistoryService storiesService;
    UserPageService userPageService;
    BillService billService;

    @Autowired
    public UserController(BillService billService, UserPageService userPageService, UserService userService, OrderService orderService, HistoryService storiesService) {
        this.userPageService = userPageService;
        this.userService = userService;
        this.orderService = orderService;
        this.storiesService = storiesService;
        this.billService=billService;
    }


    @GetMapping("/shop")
    public String shopPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "groupId", required = false) String groupId,
                           Model model,
                           @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("user", userPageService.user());
        model.addAttribute("orders", orderService.getLocaleOrders(pageable));
        if (id!=null)
            userPageService.addOrderToCart(Long.parseLong( id));
        if (groupId!=null)
            model.addAttribute("orders", orderService.getLocalOrdersByGroup(Integer.parseInt(groupId), pageable));
        return "/user/shop.html";
    }
    @GetMapping("/contacts")
    public String contactsPage(@RequestParam(value = "id", required = false) String id, Model model){
        model.addAttribute("user", userPageService.user());
        return "/user/contacts.html";
    }
    @GetMapping("/about")
    public String aboutUsPage(@RequestParam(value = "id", required = false) String id, Model model){
        model.addAttribute("user", userPageService.user());
        return "/user/about_us.html";
    }
    @GetMapping("/delivery")
    public String deliveryAndPaymentPage(@RequestParam(value = "id", required = false) String id, Model model){
        model.addAttribute("user", userPageService.user());
        return "/user/delivery_and_payment.html";
    }

    @GetMapping("/bill")
    public String billPage(@RequestParam(name = "id", required = false) String id,  Model model){
        model.addAttribute("items", billService.getLocaleBillsByStatusAndUserId( (long) ConstantBundle.getIntProperty("status.waitingForPay"), userPageService.user().getId()));
        model.addAttribute("sum", userPageService.sum());
        model.addAttribute("user", userPageService.user());
        if (id!=null){
            userPageService.payForOrderById(id);
            return "redirect:/user/bill";
        }
        return "/user/bill.html";
    }

    @GetMapping("/history")
    public String historyPage( Model model){
        model.addAttribute("user", userPageService.user());
        model.addAttribute("items", storiesService.getLocaleStoriesByUserId(userPageService.user().getId()));
        return "/user/history.html";
    }

    @GetMapping("/cart")
    public String cartPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "ok", required = false) String ok, Model model){
            model.addAttribute("order", userPageService.localOrders());
            model.addAttribute("user", userPageService.user());
            model.addAttribute("sum", userPageService.sum());
        if (id!=null) {
            userPageService.deleteOrderFromCart(Long.parseLong(id));
            return "redirect:/user/cart";
        }
        if (ok!=null) {
            userPageService.confirm();
            return "redirect:/user/cart?success=true";
        }
        return "/user/cart.html";
    }

    @GetMapping("/refill")
    public String refillPage(@RequestParam(value = "ok", required = false) String ok, Model model){
        model.addAttribute("user", userPageService.user());
        if (ok!=null) {
            userPageService.refillUserBalance();
        }
        return "/user/refill.html";
    }




}
