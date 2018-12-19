package com.baizhi.controller;

import com.baizhi.conf.ValidateImageCodeUtils;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/imageValidate")
    public void imageValidate(HttpSession session, HttpServletResponse response) throws IOException {
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("securityCode", securityCode);
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }

    @RequestMapping("/login")
    public String login(Admin admin, String enCode, HttpSession session) {
        String securityCode = (String) session.getAttribute("securityCode");

        String scode = securityCode.toLowerCase();
        String ecode = enCode.toLowerCase();
        if (scode.equals(ecode)) {
            String result = adminService.login(admin);
            return result;
        } else {
            return "ValidateCodeError";
        }

    }
}
