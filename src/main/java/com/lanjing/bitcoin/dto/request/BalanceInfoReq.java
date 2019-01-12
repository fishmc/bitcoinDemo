package com.lanjing.bitcoin.dto.request;

import lombok.Data;

@Data
public class BalanceInfoReq {
    private String address;
    private int propertyid;

    public BalanceInfoReq() {
    }

    public BalanceInfoReq(String address, int propertyid) {
        this.address = address;
        this.propertyid = propertyid;
    }
}
