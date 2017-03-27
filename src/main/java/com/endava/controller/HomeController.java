package com.endava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by vsaban on 3/27/2017.
 */
@Controller
public class HomeController {

    @GetMapping(value = {"/", "index"})
    public String homePage() {
        return "index";
    }

}
