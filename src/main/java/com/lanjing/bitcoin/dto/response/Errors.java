package com.lanjing.bitcoin.dto.response;

import lombok.Data;

@Data
public class Errors {

    /**
     * errors": [
     *     {
     *       "txid": "c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",
     *       "vout": 0,
     *       "scriptSig": "",
     *       "sequence": 4294967295,
     *       "error": "Input not found or already spent"
     *     },
     * */
    private String txid;
    private int vout;
    private String scriptSig;
    private int sequence;
    private String error;
}
