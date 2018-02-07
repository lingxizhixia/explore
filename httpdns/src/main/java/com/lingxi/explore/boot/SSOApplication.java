package com.lingxi.explore.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/sso")
public class SSOApplication {

    @RequestMapping("/auth")
    public void login(HttpServletResponse response) {
        // response.addHeader("location", "www.163.com");
        // response.setStatus(302);
        try {
            response.sendRedirect("http://me.lifesense.com:8082/toutiao/auth");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
