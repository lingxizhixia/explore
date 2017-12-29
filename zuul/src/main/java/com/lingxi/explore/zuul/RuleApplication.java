package com.lingxi.explore.zuul;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "路由规则", description = "提供添加路由规则，刷新路由规则等API")
@RestController
@RequestMapping("/rule")
public class RuleApplication {

    @Autowired
    private RuleFactory ruleFactory;

    @ApiOperation(value = "添加路由规则", notes = "添加路由规则")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "参数不正确"), @ApiResponse(code = 200, message = "SUCCESS", response = Rule.class)})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public List<Rule> addRule(@RequestBody Rule rule) {
        ruleFactory.addRule(rule);
        return ruleFactory.getRules();
    }
}
