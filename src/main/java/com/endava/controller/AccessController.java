package com.endava.controller;

import com.endava.UserDto;
import com.endava.enums.Role;
import com.endava.model.User;
import com.endava.service.DomainService;
import com.endava.service.MoneyTransferService;
import com.endava.service.UserService;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by vsaban on 6/12/2017.
 */
@Controller
public class AccessController {

    private final UserService userService;
    private final MoneyTransferService moneyTransferService;
    private final DomainService domainService;
    private final WherefromService wherefromService;

    @Autowired
    public AccessController(final UserService userService,
                            final MoneyTransferService moneyTransferService,
                            final DomainService domainService,
                            final WherefromService wherefromService) {
        this.userService = userService;
        this.moneyTransferService = moneyTransferService;
        this.domainService = domainService;
        this.wherefromService = wherefromService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(final Model model, @ModelAttribute final UserDto userDto) {

        String page;
        String message;
        if (userService.isValidUser(userDto)) {
            final User user = User.builder()
                    .withLogin(userDto.getLogin())
                    .withPassword(userDto.getPassword())
                    .withEmail(userDto.getEmail())
                    .withActive(1)
                    .withRole(Role.USER)
                    .build();

            userService.saveUser(user);

            page = "login";
            message = "Registration successful!";
        } else {

            page = "registration";
            message = "Registration failed!";
        }

        model.addAttribute("message", message);

        return page;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("domains", domainService.getDomains());
        model.addAttribute("wherefroms", wherefromService.getWherefroms());
        return "profile";
    }

    //TODO: validation messages
    @GetMapping(value = "/failure", produces = APPLICATION_JSON_VALUE)
    public String failure() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        response.sendRedirect("/login");
    }
}
