package com.endava.controller;

import com.endava.UserDto;
import com.endava.enums.Role;
import com.endava.model.User;
import com.endava.service.MoneyTransferService;
import com.endava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by vsaban on 6/12/2017.
 */
@Controller
public class AccessController {

    @Autowired
    private UserService userService;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(HttpServletRequest request, Model model, @ModelAttribute UserDto userDto) {
        User user = User.builder()
                .withLogin(userDto.getLogin())
                .withPassword(userDto.getPassword())
                .withEmail(userDto.getEmail())
                .withActive(1)
                .withRole(Role.USER)
                .build();

        String page;
        Authentication auth;

        if (userService.exists(user)) {
            model.addAttribute("exists", "This user is already registered");
            page = "registration";
        } else {
            userService.saveUser(user);

            // Authenticate the user
            auth = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), user.getAuthorities());

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            // Create a new session and add the security context.
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            page = "redirect:/profile";
        }

        return page;
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/money-transfer")
    public String moneyTransfer() {
        return "add-money-transfer";
    }

    @GetMapping("/expenses")
    public String expenses(Model model) {
        model.addAttribute("expenses", moneyTransferService.getMoneyTransferByCategory("expense"));
        return "expenses";
    }

    @GetMapping("/incomes")
    public String incomes(Model model) {
        model.addAttribute("expenses", moneyTransferService.getMoneyTransferByCategory("income"));
        return "incomes";
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
