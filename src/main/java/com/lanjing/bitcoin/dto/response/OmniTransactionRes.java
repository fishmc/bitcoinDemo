package com.lanjing.bitcoin.dto.response;

import lombok.Data;

@Data
public class OmniTransactionRes {


    /**
     * txid : 63d7e22de0cf4c0b7fd60b4b2c9f4b4b781f7fdb8be4bcaed870a8b407b90cf1
     * fee : 0.00010000
     * sendingaddress : 2N1WnASsjgwrzbucBHhMd6gHLqpipq7kUZM
     * ismine : false
     * version : 0
     * type_int : 54
     * type : Create Property - Manual
     * propertyid : 2147483664
     * divisible : true
     * propertytype : divisible
     * ecosystem : test
     * category : FooMANAGED
     * subcategory : Bar
     * propertyname : Bazz
     * data : BazzyFoo
     * url : www.bazzcoin.info
     * amount : 0.00000000
     * valid : true
     * blockhash : 000000002101ea0da161b6a63f4d1a0e37a2bd58c5aee49a3b8fd80640b09662
     * blocktime : 1409941113
     * positioninblock : 19
     * block : 279007
     * confirmations : 1133552
     */

    private String txid;//事务哈希
    private String fee;//手续费
    private String sendingaddress;//发送者
    private boolean ismine;//是否本机钱包
    private int version;
    private int type_int;
    private String type;
    private long propertyid;//令牌id
    private boolean divisible;
    private String propertytype;
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
