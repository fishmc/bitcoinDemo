package com.lanjing.bitcoin.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "BtcAddressInfo",description = "比特币地址信息")
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

    @ApiModelProperty(value = "地址",required = true)
    private String address;
    @ApiModelProperty(value = "账号",required = true)
    private String account;
    @ApiModelProperty(value = "金额",required = true)
    private double amount;
    @ApiModelProperty(value = "广播次数",required = true)
    private int confirmations;
    private String label;
    @ApiModelProperty(value = "txid",required = true)
    private List<String> txids;
}
