package com.lingxi.explore.boot;

//import com.lifesense.health.base.kit.LoggerKit;

import com.lingxi.explore.boot.kit.OkHttpDns;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/httpdns")
public class HttpDnsApplication {

    @RequestMapping("/echo")
    public String echo(@RequestParam("url") String url) {

        OkHttpClient client = new OkHttpClient.Builder().dns(OkHttpDns.getInstance()).build();
        Request request = new Request.Builder().url(url).build();
        StringBuilder result = new StringBuilder();

        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //LoggerKit.build("health_check", "", url).error("请求异常[" + e.getMessage() + "]", e);
                System.out.println("call [" + url + "] 出现异常，详情： " + e.getMessage());
                latch.countDown();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    latch.countDown();
                    throw new IOException("Unexpected code " + response);
                }
                DataInputStream dis = new DataInputStream(response.body().byteStream());
                int len;
                byte[] buff = new byte[4096];

                while ((len = dis.read(buff)) != -1) {
                    result.append(new String(buff, 0, len));
                }
                System.out.println("call [" + url + "] return " + result.toString());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (Exception e) {

        }
        return result.toString();
    }
}
