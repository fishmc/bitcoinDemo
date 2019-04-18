package com.lanjing.bitcoin.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BitcoinValAddRes",description = "比特币地址验证")
public class BitcoinValAddRes {
    /**
     * isvalid : true
     * address : mjH1iB7wt5TC4f8qjvZqtmBXd1aCPSPinC
     * scriptPubKey : 76a914293d87f697ca96ffb00f049b60645e5c8979498488ac
     * ismine : true
     * iswatchonly : false
     * isscript : false
     * pubkey : 0302cb77549243dd028915c31a0480285483dbb997573955e8e5ae78353c14a8fe
     * iscompressed : true
     * account : account0
     * hdkeypath : m/0'/0'/14'
     * hdmasterkeyid : 996e890cd8c4d3d9c2702616b1727797cd46799c
     */

    @ApiModelProperty(value = "地址的正确性")
    public boolean isvalid;
    @ApiModelProperty(value = "地址")
    public String address;
    public String scriptPubKey;
    @ApiModelProperty(value = "是否为本机")
    public boolean ismine;
    @ApiModelProperty(value = "仅为可见")
    public boolean iswatchonly;
    public boolean isscript;
    @ApiModelProperty(value = "公钥")
    public String pubkey;
    @ApiModelProperty(value = "是否经过压缩")
    public boolean iscompressed;
    @ApiModelProperty(value = "账户名")
    public String account;
    public String hdkeypath;
    public String hdmasterkeyid;
}
