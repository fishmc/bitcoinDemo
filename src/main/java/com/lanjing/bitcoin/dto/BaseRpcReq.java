package com.lanjing.bitcoin.dto;

import lombok.Data;

import java.util.Random;

@Data
public class BaseRpcReq {
    private String jsonrpc = "2.0";
    private String method;
    private Object[] params;
    private String id;

    private BaseRpcReq(String method) {
        this.method = method;
        this.id = String.valueOf(new Random().nextLong());
    }

    public static BaseRpcReq create(String method) {
        return new BaseRpcReq(method);
    }

    //只需要第一个参数必须传方法名，其余的按顺序添加即可
    public static BaseRpcReq create(String method, Object... vars) {
        BaseRpcReq omniReq = new BaseRpcReq(method);

        if (vars == null || vars.length == 0 || (vars.length == 1 && vars[0] == null)) {
            omniReq.params = null;
        } else {
            omniReq.params = vars;
        }
        return omniReq;
    }
}
