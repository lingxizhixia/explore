package com.lingxi.toutiao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("config")
@RefreshScope
public class ConfigController {

    @Value("${tokenTimeOut:5000}")
    private int tokenTimeOut;

    public void setTokenTimeOut(int tokenTimeOut) {
        this.tokenTimeOut = tokenTimeOut;
    }

    @RequestMapping(value = "/tokenTimeOut", method = GET)
    @ResponseBody
    public int get() {
        return tokenTimeOut;
    }
}
