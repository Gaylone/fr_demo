package top.sonjong.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.sonjong.system.API.ITechnicianService;
import top.sonjong.system.domain.POJO.TechnicianPOJO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TechnicianController {
    @Autowired
    private ITechnicianService technicianService;

    @RequestMapping("/portal")
    public String userLogin(String account,String pswd, HttpSession session, HttpServletResponse response) throws IOException {
        TechnicianPOJO currentUser = technicianService.userLogin(account,pswd);

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(currentUser !=null){
            session.setAttribute("currentUser",currentUser);
            return "index";
        }else{
            out.write("<script>alert('用户名或密码错误')</script>");
            return "login";

        }

    }
}
