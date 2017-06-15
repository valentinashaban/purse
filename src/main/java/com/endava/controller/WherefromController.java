package com.endava.controller;

import com.endava.model.Wherefrom;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WherefromController {

    private final WherefromService wherefromService;

    @Autowired
    public WherefromController(final WherefromService wherefromService) {
        this.wherefromService = wherefromService;
    }

    @GetMapping("/profile/add-wherefrom")
    public String addWherefrom(final Model model) {
        model.addAttribute("wherefrom", new Wherefrom());
        return "add-wherefrom";
    }

    @PostMapping("/profile/add-wherefrom")
    public String saveWherefrom(@ModelAttribute final Wherefrom wherefrom) {
        wherefromService.saveWherefrom(wherefrom);
        return "redirect:/profile";
    }

}
