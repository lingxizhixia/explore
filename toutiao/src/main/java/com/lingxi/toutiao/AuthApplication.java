package com.lingxi.toutiao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/toutiao")
public class AuthApplication {

    @RequestMapping("/auth")
    public void login(HttpServletResponse response) {
        Cookie cookie = new Cookie("uuid", "YZqydYK5M0hNqEzysu5VAYPTwgqZx2ic");
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        try {
            response.getWriter().print("hello toutiao");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
