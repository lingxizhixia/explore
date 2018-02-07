package com.lingxi.explore.boot.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.lifesense.health.base.kit.LoggerKit;
import okhttp3.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OkHttpDns implements Dns {

    private static final String HTTPDNS_URL = "http://203.107.1.33/188954/d?host={0}&ip=113.108.134.210";

    private static OkHttpDns instance;
    private static Object locker = new Object();

    private OkHttpDns() {

    }

    public static OkHttpDns getInstance() {
        if (instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    instance = new OkHttpDns();
                }
            }
        }
        return instance;
    }

    @Override
    public List<InetAddress> lookup(String dn) throws UnknownHostException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(MessageFormat.format(HTTPDNS_URL, dn)).build();
        List<InetAddress> addresses = new ArrayList<>();

        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
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
                StringBuilder result = new StringBuilder();
                while ((len = dis.read(buff)) != -1) {
                    result.append(new String(buff, 0, len));
                }

                String data = result.toString();
                //LoggerKit.build("resolve_dn_with_ali_httpdns", "", dn).put("OkHttpDnsResponse", data).info("利用阿里云HttpDNS服务解析域名[" + dn + "]");


                JSONObject json = JSON.parseObject(data);
                JSONArray ips = json.getJSONArray("ips");
                for (int i = 0; i < ips.size(); i++) {
                    addresses.add(InetAddress.getByName(ips.getString(i)));
                }

                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (Exception e) {

        }

        return addresses;
    }
}
