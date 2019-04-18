package com.lanjing.bitcoin.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TransactionRes",description = "交易对象")
public class OmniTransactionRes {


   /**
    * {
    *   "txid" : "hash",                 // (string) the hex-encoded hash of the transaction
    *   "sendingaddress" : "address",    // (string) the Bitcoin address of the sender
    *   "referenceaddress" : "address",  // (string) a Bitcoin address used as reference (if any)
    *   "ismine" : true|false,           // (boolean) whether the transaction involes an address in the wallet
    *   "confirmations" : nnnnnnnnnn,    // (number) the number of transaction confirmations
    *   "fee" : "n.nnnnnnnn",            // (string) the transaction fee in bitcoins
    *   "blocktime" : nnnnnnnnnn,        // (number) the timestamp of the block that contains the transaction
    *   "valid" : true|false,            // (boolean) whether the transaction is valid
    *   "positioninblock" : n,           // (number) the position (index) of the transaction within the block
    *   "version" : n,                   // (number) the transaction version
    *   "type_int" : n,                  // (number) the transaction type as number
    *   "type" : "type",                 // (string) the transaction type as string
    *   [...]                            // (mixed) other transaction type specific properties
    * }
    * */
    @ApiModelProperty(value = "txid",required = true)
    private String txid;//事务哈希
    @ApiModelProperty(value = "手续费",required = true)
    private String fee;//手续费
    @ApiModelProperty(value = "发送者",required = true)
    private String sendingaddress;//发送者
    private String referenceaddress;
    @ApiModelProperty(value = "是否为本机钱包",required = true)
    private boolean ismine;//是否本机钱包
    private int version;
    private int type_int;
    private String type;
    @ApiModelProperty(value = "代币id",required = true)
    private long propertyid;//令牌id
    private boolean divisible;
    private String propertytype;
    @ApiModelProperty(value = "是否为测试网络",required = true)
    private String ecosystem;//生态网络
    private String category;
    private String subcategory;
    private String propertyname;
    private String data;
    private String url;
    private String amount;
    private boolean valid;
    private String blockhash;
    private int blocktime;
    private int positioninblock;
    private int block;
    private int confirmations;

}
