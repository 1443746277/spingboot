package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.User;
import com.qf.pojo.login;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("userlist")
    public String index(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        PageHelper.startPage(pageNum, 4);
        List<User> userList = userService.getuserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(int uid) {
        int i = userService.deleteUser(uid);
        if (i > 0) {
            return "success";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("updatePage")
    public String updatePage(int uid, HttpServletRequest request) {
        System.out.println(uid);
        User user = userService.getUserById(uid);
        request.setAttribute("user", user);
        return "update";
    }

    @RequestMapping("update")
    public String update(HttpServletRequest request, int uid, String uname, User user) {
//user.setUid(uid);
        user.setUname(uname);
        //  System.out.println(user);
        int i = userService.updateUser(user);
        System.out.println(i);
        if (i > 0) {
            return "redirect:userlist";

        } else {
            return "redirect:userlist";
        }
    }

    @RequestMapping("add")
    public String add() {
        return "adduser";
    }

    @RequestMapping("addUser")
    public String addUser(String uname, User user) {
        user.setUname(uname);
        int i = userService.addUser(user);
        if (i > 0) {
            return "redirect:userlist";
        } else {
            return "adduser";
        }
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("login")
    public String login(login login) {
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(login.getUsername(), login.getPassword());
        try {
            subject.login(usernamePasswordToken);

            if (subject.isAuthenticated()) {
                //System.out.println("到这了");
                return "redirect:userlist";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }

    @RequestMapping("unauth")
    public String fail() {
        return "fail";
    }

}