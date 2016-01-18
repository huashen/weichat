package com.lhs.weichat.controller.mobile;

import com.lhs.weichat.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController
 *
 * @author longhuashen
 * @since 16/1/14
 */
@Controller
@RequestMapping("/mobile")
public class MobileController extends BaseController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index() {
        return "mobile/register";
    }
}
