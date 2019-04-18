package com.lanjing.bitcoin.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PropertyInfoRes",description = "代币信息")
public class OmniPropertyInfoRes {

    /**
     *{
     *   "propertyid" : n,               // (number) the identifier
     *   "name" : "name",                // (string) the name of the tokens
     *   "category" : "category",        // (string) the category used for the tokens
     *   "subcategory" : "subcategory",  // (string) the subcategory used for the tokens
     *   "data" : "information",         // (string) additional information or a description
     *   "url" : "uri",                  // (string) an URI, for example pointing to a website
     *   "divisible" : true|false,       // (boolean) whether the tokens are divisible
     *   "issuer" : "address",           // (string) the Bitcoin address of the issuer on record
     *   "creationtxid" : "hash",        // (string) the hex-encoded creation transaction hash
     *   "fixedissuance" : true|false,   // (boolean) whether the token supply is fixed
     *   "managedissuance" : true|false, // (boolean) whether the token supply is managed by the issuer
     *   "freezingenabled" : true|false, // (boolean) whether freezing is enabled for the property (managed properties only)
     *   "totaltokens" : "n.nnnnnnnn"    // (string) the total number of tokens in existence
     * }
     */
    @ApiModelProperty(value = "代币id",required = true)
    private int propertyid;
    @ApiModelProperty(value = "代币名称",required = true)
    private String name;
    @ApiModelProperty(value = "代币类型",required = true)
    private String category;
    @ApiModelProperty(value = "子类型",required = true)
    private String subcategory;
    @ApiModelProperty(value = "代币信息",required = true)
    private String data;
    @ApiModelProperty(value = "代币官网链接",required = true)
    private String url;
    @ApiModelProperty(value = "是否可以切分",required = true)
    private boolean divisible;
    @ApiModelProperty(value = "发起账号",required = true)
    private String issuer;
    @ApiModelProperty(value = "创建txid",required = true)
    private String creationtxid;
    private boolean fixedissuance;
    private boolean managedissuance;
    private boolean freezingenabled;
    @ApiModelProperty(value = "发行量",required = true)
    private String totaltokens;
}
