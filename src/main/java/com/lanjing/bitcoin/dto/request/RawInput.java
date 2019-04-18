package com.lanjing.bitcoin.dto.request;

import lombok.Data;

@Data
public class RawInput {

    private String txid;

    private int vout;
}
