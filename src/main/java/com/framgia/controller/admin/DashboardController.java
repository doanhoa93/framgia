package com.framgia.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("private")
public class DashboardController {

    @GetMapping
    public String dashboard(Model model){
        return "admin/index";
    }

    @GetMapping("home")
    public String home(Model model){
        return "admin/index";
    }

    @GetMapping("/properties")
    public String getProperties(Model model){
        return "admin/index";
    }

    @PostMapping("/properties")
    public String updateProperties(Model model){
        return "admin/index";
    }
}
