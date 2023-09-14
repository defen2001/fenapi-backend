package com.defen.fenapiclientsdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.defen.fenapiclientsdk.model.User;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.defen.fenapiclientsdk.utils.SignUtils.genSign;


/**
 * 调用第三方接口的客户端
 *
 * @author defen
 */
public class FenApiClient {

    private static String GATEWAY_HOST = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public FenApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void setGatewayHost(String gatewayHost) {
        GATEWAY_HOST = gatewayHost;
    }


    private Map<String, String> getHeaderMap(String body, String method) throws UnsupportedEncodingException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(10));
        hashMap.put("sign", genSign(body, secretKey));
        body = URLUtil.encode(body, CharsetUtil.CHARSET_UTF_8);
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("method", method);
        return hashMap;
    }


    public String invokeInterface(String params, String url, String method) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .header("Accept-Charset", CharsetUtil.UTF_8)
                .addHeaders(getHeaderMap(params, method))
                .body(params)
                .execute();
        return JSONUtil.formatJsonStr(httpResponse.body());
    }
}
