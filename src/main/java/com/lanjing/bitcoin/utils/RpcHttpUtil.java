package com.lanjing.bitcoin.utils;

import com.alibaba.fastjson.JSON;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.lanjing.bitcoin.dto.BaseRPCresponse;
import com.lanjing.bitcoin.dto.BaseRpcReq;
import com.lanjing.bitcoin.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RpcHttpUtil {
    //private static final String RPC_HOST = "http://156.233.68.81";
    private static final String RPC_HOST = "http://localhost";
    private static final String RPC_PORT = "8332";
    private String RPC_USER = "lanjing";

    private String RPC_PASSWORD = "root";


    private static final String URL = RPC_HOST + ":" + RPC_PORT;
    private JsonRpcHttpClient mClient;
    private RestTemplate mClient2;

    public RpcHttpUtil() {
        String cred = Base64.encodeBase64String((RPC_USER + ":" + RPC_PASSWORD).getBytes());
        Map<String,String> headers = new HashMap<>(1);
        headers.put("Authorization", "Basic " + cred);
        try {
            this.mClient = new JsonRpcHttpClient(new URL(URL),headers);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String engine(@NotNull String method, Object... var) {
        return engine(method, String.class, var);
    }

    public <T> T engine(@NotNull String method, Class<T> clazz, Object... var) {
        try {

            return mClient.invoke(method, var, clazz);
        } catch (Throwable throwable) {
            String message = throwable.getMessage();
            Throwable cause = throwable.getCause();
            log.error("error cause=" + cause.getMessage());
            log.error("error message=" + throwable.getMessage());
            try {
                BaseRPCresponse res = JSON.parseObject(message, BaseRPCresponse.class);
                BaseRPCresponse.ErrorBean error = res.getError();
                throw new BaseException(String.valueOf(error.getCode()), error.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new BaseException("W000", e.getMessage());
            }
        }
    }

    public <T> BaseRPCresponse<T> engine2(@NotNull String method) {
        try {
            BaseRpcReq baseRpcReq = BaseRpcReq.create(method);
            return mClient2.postForObject(URL, baseRpcReq, BaseRPCresponse.class);
        } catch (Throwable throwable) {
            throw new BaseException(throwable.getMessage());
        }
    }

}
