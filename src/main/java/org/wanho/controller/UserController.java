package org.wanho.controller;

import org.wanho.entity.User;
import org.wanho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired(required = false)
    UserMapper mapper;

    @RequestMapping("/login")
    public int login(@RequestBody User user, HttpSession session) {
        System.out.println("user = " + user);
        User u = mapper.selectByUsername(user.getUsername());
        if (u != null) {
            if (user.getPassword().equals(u.getPassword())) {
                session.setAttribute("user", u);
                return 1;
            }
            return 3;
        }
        return 2;
    }

    @RequestMapping("/currentUser")
    public User currentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user;
    }
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");
    }
}
