package com.wym.demo.controller;

import com.wym.demo.pojo.User;
import com.wym.demo.result.ResultUtils;
import com.wym.demo.service.UserService;
import com.wym.demo.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(String error) {
//        if(!StringUtils.isEmpty(error)){
//            return "loginError";
//        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }


    @GetMapping("/registerError")
    public String registerError() {
        return "registerError";
    }

    @GetMapping("/updateError")
    public String updateError() {
        return "updateError";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @GetMapping("/update")
    public String updateUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "update";
    }

    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password")String password){
        System.out.println("开始了"+username+password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String  token = null;
        try {
            Map<String, Object> map = userService.loginUser(user);
            User admin = (User)map.get("admin");
            HttpSession session = request.getSession();
            //session存储用户信息
            session.setAttribute("user",admin);
        } catch (Exception e) {
            return "loginError";
        }
        return "home";
    }

    @PostMapping("/doRegister")
    @ResponseBody
    @CrossOrigin
    public ResultUtils doRegister(@RequestParam("username") String username,@RequestParam("password") String password,
                                       @RequestParam("email") String email, @RequestParam("birthday") String birthday) {
           User user = new User();
       user.setUsername(username);
       user.setPassword(password);
       user.setEmail(email);
       user.setBirthday(birthday);
        userService.registerUser(user);
        return new ResultUtils("success","0");
    }

    @PostMapping("/doUpdate")
    @ResponseBody
    public ResultUtils doUpdate(@RequestParam("id") Long id,@RequestParam("username") String username,@RequestParam("password") String password,
                                  @RequestParam("email") String email, @RequestParam("birthday") String birthday) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(Md5Utils.md5Password(password, "abc"));
        user.setEmail(email);
        user.setBirthday(birthday);
        userService.updateUser(user);
        return new ResultUtils("success","0");
    }
}
