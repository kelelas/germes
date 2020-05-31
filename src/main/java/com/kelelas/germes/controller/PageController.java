package com.kelelas.germes.controller;


import com.kelelas.germes.entity.RoleType;
import com.kelelas.germes.entity.User;
import com.kelelas.germes.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PageController {
    OrderService orderService;
    HttpServletRequest request;
    @Autowired
    public PageController(OrderService orderService, HttpServletRequest request) {
        this.orderService = orderService;
        this.request=request;
    }

    @GetMapping("/afterlogin")
    public RedirectView mainPage(){
        RedirectView redirectView = new RedirectView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        request.getSession().setAttribute("user", user);

        if (user.getRole().equals(RoleType.ADMIN)){
            redirectView.setUrl("/admin/orders_list");

        }else if (user.getRole().equals(RoleType.USER)){
            redirectView.setUrl("/user/shop ");
        }else {
            redirectView.setUrl("/");
        }
        return redirectView;
    }

}
