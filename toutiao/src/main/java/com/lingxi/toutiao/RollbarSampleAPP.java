package com.lingxi.toutiao;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;

public class RollbarSampleAPP {

    private static Rollbar rollbar;

    public static void main(String[] args) {
        Config config = withAccessToken("e9841b44042b491c9cf8c12b05dd1bad")
                .environment("production")
                .codeVersion("1.0.0")
                .build();
        rollbar = Rollbar.init(config);

        rollbar.info("应用启动......");

        int a = 10;
        int b = 0;
        double c = 0;

        try {
            c = a / b;
        } catch (Exception e) {
            rollbar.error(e, "计算两个数值的商异常");
        }

        int flag = 0;
        while (flag <= 10) {
            flag++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                rollbar.error(e, "系统异常");
            }
        }
    }
}
