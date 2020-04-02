package xh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xh.model.User;
import xh.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

//    @Autowired
//    private User testUser;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest httpRequest){
        //TODO 校验
        User user = loginService.queryByLogin(username, password);
        if (user == null ){
            return "login";
        }
        //通过校验后的逻辑
        HttpSession httpSession = httpRequest.getSession(true);
        System.out.println("queryByLogin::"+user);
        httpSession.setAttribute("user", user);
        return "redirect:/";
    }


}
