package com.endava.controller;

import com.endava.model.Wherefrom;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "manage-wherefrom";
    }

    @PostMapping("/profile/add-wherefrom")
    public String saveWherefrom(@ModelAttribute final Wherefrom wherefrom) {
        wherefromService.saveWherefrom(wherefrom);
        return "redirect:/profile";
    }

    @GetMapping("/profile/delete-wherefrom-{id}")
    public String deleteWherefrom(@PathVariable final Long id) {
        wherefromService.deleteWherefrom(wherefromService.getWherefromById(id));
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit-wherefrom-{id}")
    public String editWherefrom(@PathVariable final Long id, final Model model) {
        model.addAttribute("wherefrom", wherefromService.getWherefromById(id));
        return "manage-wherefrom";
    }

    @PostMapping("/profile/edit-wherefrom-{id}")
    public String submitEditDomain(@ModelAttribute final Wherefrom wherefrom) {
        wherefromService.updateWherefrom(wherefrom);
        return "redirect:/profile";
    }

}
