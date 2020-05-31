package com.kelelas.germes.controller;


import com.kelelas.germes.config.ConstantBundle;
import com.kelelas.germes.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    HistoryService storiesService;
    AdminPageService adminPageService;

    public AdminController() {
    }
    @Autowired
    public AdminController(AdminPageService adminPageService, HistoryService storiesService) {
        this.adminPageService = adminPageService;
        this.storiesService = storiesService;
    }

    @RequestMapping("/orders_list")
    public String ordersPage(@RequestParam(name = "id", required = false) String id, Model model){
        model.addAttribute("items", storiesService.getLocaleStoriesByStatus((long) ConstantBundle.getIntProperty("status.waitingForConfirm")));
        if (id!=null) {
            try {
                adminPageService.updateStoryById(id);
                return "redirect:/admin/orders_list";
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return "/admin/orders_list.html";
    }

    @GetMapping("/statistics")
    public String statisticsPage( Model model){
        model.addAttribute("items",storiesService.getLocaleStories());
        return "/admin/statistics.html";
    }



}

