package com.lingxi.explore.zuul;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RuleFactory {

    private static List<Rule> rules = new ArrayList<>();

    @Autowired
    private RefreshRouteService refreshRouteService;


    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        if (!validate(rule)) {
            return;
        }
        long count = rules.stream().filter(t -> t.getPath().equalsIgnoreCase(rule.getPath())).count();
        if (count >= 1) {
            return;
        }
        rules.add(rule);

        refreshRouteService.refreshRoute();
    }

    private boolean validate(Rule rule) {
        if (rule == null) {
            return false;
        }
        if (StringUtils.isEmpty(rule.getPath())) {
            return false;
        }
        if (StringUtils.isEmpty(rule.getUrl())) {
            return false;
        }
        return true;
    }
}
