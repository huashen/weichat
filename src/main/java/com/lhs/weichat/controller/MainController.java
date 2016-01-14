package com.lhs.weichat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MainController
 *
 * @author longhuashen
 * @since 15/12/11
 */
@Controller
@RequestMapping("/admin/mobile")
public class MainController {

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String index() {
        return "mobile/index";
    }
}
