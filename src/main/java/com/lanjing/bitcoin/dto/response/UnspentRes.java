package com.lanjing.bitcoin.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UnspentRes",description = "UTXO对象")
public class UnspentRes {

    /**
     * txid : fe90733870abaf5cf6ca2a72171fa1e976470a3098c20d0d34892bad1a378a05
     * vout : 0
     * address : mpaumxor659PhoJhXp1VCVHVwbFCZSRmuf
     * account : yepeng
     * scriptPubKey : 76a9146378b17c611612a678ea0db33cb20a1941a5188588ac
     * amount : 0.55275278
     * confirmations : 825
     * spendable : true
     * solvable : true
     */

    private String txid;
    private int vout;
    private String address;
    private String account;
    private String scriptPubKey;
    private double amount;
    private int confirmations;
    private boolean spendable;
    private boolean solvable;

    private double value;

    public UnspentRes() {
    }

    public UnspentRes(String txid, int vout){
        this.txid = txid;
        this.vout = vout;
    }

    public UnspentRes(String txid,int vout,String scriptPubKey,double value){
        this.txid = txid;
        this.vout = vout;
        this.scriptPubKey = scriptPubKey;
        this.value = value;
    }
}
