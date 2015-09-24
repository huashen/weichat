package com.lhs.weichat.action;

import com.lhs.weichat.bean.Attachement;
import com.lhs.weichat.bean.Result;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.service.IAttachemntService;
import com.lhs.weichat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * UserAction
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAttachemntService attachemntService;

    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public Result register(HttpServletRequest request,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "account", defaultValue = "") String account,
                           @RequestParam(value = "password", defaultValue = "") String password,
                           @RequestParam(value = "birthday") long brithday,
                           @RequestParam(value = "gender", defaultValue = "1") int gender,
                           @RequestParam(value = "signature", defaultValue = "") String signature,
                           @RequestParam(value = "avatarId", defaultValue = "") int avatarId) {

        String message = "";
        boolean success = true;
        if(name == null || name.isEmpty()) {
            message = message + "昵称不能为空";
            success = false;
        }

        if(account == null || account.isEmpty()) {
            message = message + "账户不能为空";
            success = false;
        }else {
            User user = userService.getUserByAccount(account);
            if(user != null && user.getId() > 0) {
                message = message + "账户[" + account + "]已存在";
                success = false;
            }
        }

        if(password == null || password.isEmpty()) {
            message = message + "密码不能为空!";
            success = false;
        } else if(password.length() < 6){
            message = message + "密码至少6位！";
            success = false;
        }

        User u = null;
        Attachement a = null;
        if(success) {
            if(avatarId > 0) {
                a = attachemntService.getAttachementById(avatarId);
            }
            User newUser = new User();
            newUser.setName(name);
            newUser.setAccount(account);
            newUser.setPassword(password);
            newUser.setBirthday(new Date(brithday));
            newUser.setSignature(signature);
            newUser.setGender(gender);
            newUser.setAvatar(a);
            u = userService.addUser(newUser);
        }

        Result result = new Result(success);
        result.setMessage(message);
        result.setObj(u);
        return result;
    }
}
