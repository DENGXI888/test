package com.springmvc.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // 处理登录页面请求
    @GetMapping("/login")
    public String showLoginPage(Model model, HttpServletRequest request) {
        // 检查是否有错误消息
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            // 清除错误消息，避免刷新页面后仍显示
            request.getSession().removeAttribute("errorMessage");
        }
        return "studentpages/login";
    }

    // 处理登录验证
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session, Model model) {

        // 模拟用户数据库（实际应用中应从数据库查询）
        if ("admin".equals(username) && "123456".equals(password)) {
            // 验证成功，创建用户对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole("管理员");

            // 将用户信息存入session
            session.setAttribute("user", user);

            // 重定向到主页
            return "redirect:/home";
        } else {
            // 验证失败，设置错误消息
            session.setAttribute("errorMessage", "用户名或密码错误");
            // 重定向回登录页面
            return "redirect:/login";
        }
    }

    // 处理主页请求
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        // 检查用户是否已登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 用户未登录，重定向到登录页面
            return "redirect:/login";
        }

        // 将用户信息传递给视图
        model.addAttribute("user", user);
        return "studentpages/home";
    }

    // 处理退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 销毁session
        session.invalidate();
        // 重定向到登录页面
        return "redirect:/login";
    }
}