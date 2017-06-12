package com.endava.controller;

import com.endava.enums.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by vsaban on 6/12/2017.
 */
@Controller
public class AccessController {

    @Secured({Role.Names.ADMIN, Role.Names.USER})
    @PostMapping(value = "/success", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> success(Authentication authentication) {
        return new ResponseEntity<>(getRoles((List<GrantedAuthority>) authentication.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping(value = "/failure", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> failure() {
        return new ResponseEntity<>("Something went wrong", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/access-denied", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> accessDenied() {
        return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
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

    private List<String> getRoles(List<GrantedAuthority> authorities) {

        return authorities.stream().map(GrantedAuthority::getAuthority).collect(toList());
    }
}