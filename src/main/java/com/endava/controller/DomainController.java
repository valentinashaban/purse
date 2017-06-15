package com.endava.controller;

import com.endava.model.Domain;
import com.endava.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DomainController {

    private final DomainService domainService;

    @Autowired
    public DomainController(final DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/profile/add-domain")
    public String addDomain(final Model model) {
        model.addAttribute("domain", new Domain());
        return "manage-domain";
    }

    @PostMapping("/profile/add-domain")
    public String saveDomain(@ModelAttribute final Domain domain) {
        domainService.saveDomain(domain);
        return "redirect:/profile";
    }

    @GetMapping("/profile/delete-domain-{id}")
    public String deleteDomain(@PathVariable final Long id) {
        domainService.deleteDomain(domainService.getDomainById(id));
        return "redirect:/profile";
    }

    @GetMapping("/profile/edit-domain-{id}")
    public String editDomain(@PathVariable final Long id, final Model model) {
        model.addAttribute("domain", domainService.getDomainById(id));
        return "manage-domain";
    }

    @PostMapping("/profile/edit-domain-{id}")
    public String submitEditDomain(@ModelAttribute final Domain domain) {
        domainService.updateDomain(domain);
        return "redirect:/profile";
    }

}
