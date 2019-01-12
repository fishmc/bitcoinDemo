package com.lanjing.bitcoin.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BtcAddressInfo {


    /**
     * address : mh7tRuxwdJmeYzRai8vm377pi4K3SLTdM2
     * account : account2
     * amount : 1.37062639
     * confirmations : 1016
     * label : account2
     * txids : ["d8c4935a32796de29aad522fcd4e460f796ecf520cd50a3a36c28ce3c2e95130",
     * "d5ef21cd7430d603774e063a70707aeb241e080e475733b3eed5f8ac0f388f9b"]
     */

    private String address;
    private String account;
    private double amount;
    private int confirmations;
    private String label;
    private List<String> txids;
}
