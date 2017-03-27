package com.endava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by vsaban on 3/27/2017.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }

}
