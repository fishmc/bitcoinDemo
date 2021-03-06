package com.lanjing.bitcoin.dao;

import com.lanjing.bitcoin.dto.request.RawInput;
import com.lanjing.bitcoin.dto.response.*;
import com.lanjing.bitcoin.exception.E;
import com.lanjing.bitcoin.utils.AssertUp;
import com.lanjing.bitcoin.utils.RpcHttpUtil;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Repository
public class BitcoinDao {

    private RpcHttpUtil http;


    public BitcoinDao() {
            http = new RpcHttpUtil();
    }


    /***
     * 验证地址合法性
     * @param: [address]
     * @return: java.lang.String
     **/
    public BitcoinValAddRes validateAddress(String address) {
        return http.engine("validateaddress", BitcoinValAddRes.class, address);
    }


    /***
     * 生成新的地址,usdt与bit通用地址
     * @param: [account] 账户名，如果不设置默认为空
     * @return: java.lang.String 地址
     **/
    public String getnewaddress(@Nullable String account) {
        if (StringUtils.isEmpty(account)) {
            return http.engine("getnewaddress", String.class);
        } else {
            return http.engine("getnewaddress", String.class, account);
        }


    }

    /***
     * 获取账户名称下的地址列表
     * @param: [account]
     * @return: java.lang.String
     **/
    public String[] getAddressesByAccName(String accountName) {
        return http.engine("getaddressesbyaccount", String[].class, accountName);
    }


    /***
     * 获取地址对于的账户名称 by address
     * @param: [address]
     * @return: java.lang.String
     **/
    public String getAccountByAddress(String address) {
        AssertUp.isTrue(validateAddress(address).isvalid, E.ADDRESS_ERROR);
        return http.engine("getaccount", String.class, address);
    }


    /***
     * 获取所有账户下的比特币余额，或者指定账户下的余额
     * @param: [account]
     * @return: java.lang.String
     **/
    public String getBalance(@Nullable java.lang.String account) {
        if (StringUtils.isEmpty(account)) {
            return http.engine("getbalance", String.class);
        } else {
            return http.engine("getbalance", account);
        }
    }

    /***
     * 查询指定地址的收款总额（不是余额！不是余额！不是余额）
     * @param:地址
     * @return:收款总额
     **/
    public String getReceivedByAddress(String address) {
        return http.engine("getreceivedbyaddress", address);
    }


    /***
     * 获取UTXo
     * @param: [address]
     * @return: void
     **/
    public UnspentRes[] listUnSpent(@Nullable java.lang.String address) {

        AssertUp.isTrue(address == null || validateAddress(address).isvalid, E.ADDRESS_ERROR);
        if (StringUtils.isEmpty(address)) {
            UnspentRes[] listunspent = http.engine("listunspent", UnspentRes[].class);
            return listunspent;
        } else {
            Object[] parms = {0, 999999, new java.lang.String[]{address}};//最小确认，最大确认
            UnspentRes[] listunspent = http.engine("listunspent", UnspentRes[].class, parms);
            return listunspent;
        }
    }


    /****************************Step 7 ****************************************/
    /***
     * 创建一个 payload ,注意amount需要是String类型
     * @param:
     * [
     * propertyid:令牌id
     * amount:金额
     * ]
     * @return: String
     **/
    public String payload(int propertyid,double amount){
        AssertUp.isTrue(amount > 0, E.AMOUNT_INPUT_EEROR);
        return http.engine("omni_createpayload_simplesend",String.class,propertyid,String.valueOf(amount));
    }

    /***
     * 构建交易
     * @param:
     * [
     * List<UnspentRes>:UTXOS
     * ]
     * @return: String
     **/
    public String Createrawtransaction(UnspentRes[] UTXOs,String fromAddress,String toAddress,double amount,double fee){
        //创建BTC交易

        //parm1:Inputs
        RawInput[] rawInputs = new RawInput[UTXOs.length];
        RawInput rawInput = new RawInput();
        for (int i = 0; i < UTXOs.length ; i++) {
            rawInput.setTxid(UTXOs[i].getTxid());
            rawInput.setVout(UTXOs[i].getVout());
            rawInputs[i] = rawInput;
        }

        //parm2:The address and amounts to pay fee
        HashMap<String,String> parm2 = new HashMap();
        parm2.put(toAddress, String.valueOf(amount));
        parm2.put(fromAddress, String.valueOf(fee));

        HashMap parm3 = new HashMap();

//        createrawtransaction
//    '[
//        { "txid" : "245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b", "vout" : 0 },
//          { "txid" : "ba2647bb6eb71e4e7e152b846485818d2445e83d3173b14a14ccb21736ab616f", "vout" : 1 },
//          { "txid" : "1a679597b984addf4c67e47c3a49482e691bbbd600b78d132264d23f474c09fd", "vout" : 0 }
//    ]'
//    '{"my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w": 0.025}'

      //          createrawtransaction '[ { "txid" : "245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b", "vout" : 0 }, { "txid" : "ba2647bb6eb71e4e7e152b846485818d2445e83d3173b14a14ccb21736ab616f", "vout" : 1 }, { "txid" : "1a679597b984addf4c67e47c3a49482e691bbbd600b78d132264d23f474c09fd", "vout" : 0 } ]' '{"my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w": 0.025}'


       return http.engine("createrawtransaction", String.class , rawInputs, parm2 );
    }

    /***
     * 附加输出交易，step2和step3
     * @param:
     * [
     * createrawtransaction:Step3的输出
     * payload：step2的输出
     * ]
     * @return: String
     **/
    public String omniCreaterawtxOpreturn(String createrawtransaction,String payload){
        return http.engine("omni_createrawtx_opreturn",String.class,createrawtransaction,payload);
    }

    /***
     * 附加输入交易，step4和输出地址
     * @param:
     * [
     * omniCreaterawtxOpreturn:Step4的输出
     * toAddress：输出地址
     * ]
     * @return: String
     **/
    public String omniCreaterawtxReference(String omniCreaterawtxOpreturn,String toAddress){
        AssertUp.isTrue(toAddress == null || validateAddress(toAddress).isvalid, E.ADDRESS_ERROR);
        return http.engine("omni_createrawtx_reference",String.class,omniCreaterawtxOpreturn,toAddress);
    }

    /***
     * 指定gas费的地址
     * @param:
     * [
     * reference:Step5的输出
     * fromAddress：fee地址
     * fee:gas费用
     * List<UnspentRes>：需要带上密钥
     * ]
     * @return: String
     **/
    public String omniCreaterawtxChange(String reference, String fromAddress,double fee,List<UnspentRes> UTXOs){
        AssertUp.isTrue(fromAddress == null || validateAddress(fromAddress).isvalid, E.ADDRESS_ERROR);
        Map[] args1 = new Map[2];
        int flag = 0;

        for(UnspentRes UTXO : UTXOs){
            Map input = new HashMap();
            input.put("txid" ,UTXO.getTxid());
            input.put("vout" ,UTXO.getVout());
            input.put("scriptPubKey", UTXO.getScriptPubKey());
            input.put("value",UTXO.getValue());
            args1[flag] = input;
            flag ++ ;

        }

        return http.engine("omni_createrawtx_change",String.class,reference,args1,fromAddress,fee);
    }

    /***
     * 签署交易
     * @param:
     * [
     * tx_change:Step6的输出
     * ]
     * @return: String
     **/
    public Signrawtransaction signrawtransaction(String tx_change){
        return http.engine("signrawtransaction", Signrawtransaction.class,tx_change);
    }

    /***
     * 广播交易
     * @param:
     * [
     * signrawtransaction:Step7的输出
     * ]
     * @return: String
     **/
    public String sendrawtransaction(String signrawtransaction) {

        return http.engine("sendrawtransaction", String.class, signrawtransaction);
    }

    public String sendtoaddress(String toAddress,double amount){
        AssertUp.isTrue(validateAddress(toAddress).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(amount > 0, E.AMOUNT_INPUT_EEROR);
        return http.engine("sendtoaddress",String.class,toAddress, String.valueOf(amount));
    }






    /***
     * 获取钱包内比特币地址信息列表
     * @param: []
     * @return: void
     **/
    public List<BtcAddressInfo> listReceivedByAddress() {
        return http.engine("listreceivedbyaddress", List.class, 1, true);
    }


/**************************************USDT层********************************/


    /**
     * 列出所有令牌或智能属性。
     */
    public List<OmniPropertyInfoRes> getOmniListProperties() {
        return http.engine("omni_listproperties", List.class);
    }

    /***
     * 查询Omni状态信息。
     * @param: []
     * @return: java.lang.String
     **/
    public OmniInfoRes getOmniInfo() {
        return http.engine("omni_getinfo", OmniInfoRes.class);
    }


    /***
     * 查询令牌属性。
     * @param: [propertyid]
     * @return: java.lang.String
     **/
    public OmniPropertyInfoRes getOmniPropertyById(int propertyid) {
        return http.engine("omni_getproperty", OmniPropertyInfoRes.class, propertyid);

    }

    /***
     * 查询令牌余额信息 by 地址&id
     * @param: [address, propertyid]
     * @return: java.lang.String
     **/
    public OmniTokenBalanceInfoRes getBalanceByAddAndId(String address, int propertyid) {

        AssertUp.isTrue(validateAddress(address).isvalid, E.ADDRESS_ERROR);
        return http.engine("omni_getbalance", OmniTokenBalanceInfoRes.class, address, propertyid);
    }

    /***
     *查询令牌余额信息 by id
     Returns a list of token balances for a given currency or property identifier.
     * @param: [token_id]
     * @return: void
     **/
    @Deprecated
    public List<OmniTokenBalanceInfoRes> getOmniBalanceById(int token_id) {
        return http.engine("omni_getallbalancesforid", List.class, token_id);
    }


    /***
     * 查询令牌余额信息 by 地址
     * @param: [address]
     * @return:
     */
    public List<OmniTokenBalanceInfoRes> getOmniBalanceByAddress(String address) {


        AssertUp.isTrue(validateAddress(address).isvalid, E.ADDRESS_ERROR);

        return http.engine("omni_getallbalancesforaddress", List.class, address);
    }




    /***
     * 创建并发送资助的简单发送交易。
     * 来自发件人的所有比特币都被消费，如果缺少比特币，则从指定的费用来源获取。更改将发送到费用来源！
     * @param: [
     * fromaddress 发送地址,
     * toaddress 接收地址,
     * propertyid 令牌id,
     * amount =金额,
     * feeaddress 支付手续费的地址
     * ]
     * @return: java.lang.String
     **/
    public String sendOmniToken(String fromaddress,
                                String toaddress,
                                int propertyid,
                                double amount,
                                String feeaddress) {
        AssertUp.isTrue(validateAddress(feeaddress).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(validateAddress(toaddress).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(validateAddress(fromaddress).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(amount > 0, E.AMOUNT_INPUT_EEROR);

        return http.engine("omni_funded_send", fromaddress, toaddress, propertyid, String.valueOf(amount), feeaddress);
    }


    /***
     * 创建并发送将给定生态系统中的所有可用令牌传输给收件人的事务，全部发送
     * @param:
     * [
     * fromaddress:令牌发送者地址
     * toaddress:令牌接受者地址
     * ecosystem:生态，1-正式生态，2-测试生态
     * feeaddressx:用于支付手续费的地址
     * ]
     * @return: void
     **/
    public String sendOmniTokenAll(String fromaddress, String toaddress, boolean is_main_ecosystem, String feeaddressx) {
        AssertUp.isTrue(validateAddress(feeaddressx).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(validateAddress(toaddress).isvalid, E.ADDRESS_ERROR);
        AssertUp.isTrue(validateAddress(fromaddress).isvalid, E.ADDRESS_ERROR);

        return http.engine("omni_funded_sendall", String.class, fromaddress, toaddress, is_main_ecosystem ? 1 : 2, feeaddressx);
    }


    /***
     * 获取有关Omni事务的详细信息。
     * 【全网】
     * @param: [txid]：需要查找事务的哈希值
     * @return: void
     **/
    public OmniTransactionRes getOmniTransaction(String txid) {
        return http.engine("omni_gettransaction", OmniTransactionRes.class, txid);
    }


    /**
     * 列出钱包事务，可选地按地址和块边界过滤。充值事件在这个方法的返回中
     * List wallet transactions, optionally filtered by an address and block boundaries.
     * 【本机】
     * 不建议直接使用这个方法
     *
     * @param: String txid      可选的	地址过滤（默认："*"）
     * int count	    可选的	显示最多n事务（默认：10）
     * skip             可选的	跳过第n个事务（默认：0）
     * int startblock	可选的	第一个块开始搜索（默认：0）
     * int endblock;    可选的 最后一个块中搜索包括（默认值：999999999）
     * @return: java.lang.String
     **/
    @Deprecated
    public List<OmniTransactionRes> listOmniTransactions(String tx, int count, int skip, int start, int end) {
        return http.engine("omni_listtransactions", List.class, tx, count, skip, start, end);
    }

    /***
     * 列出【本机】钱包事务，可选地按地址和块边界过滤。充值事件在这个方法的返回中
     * 默认实现
     * @param: [address：相关地址，如果为空则不过滤]
     * @return: java.lang.String，返回的结果中 ismine为true代表与本钱包中的地址有关系
     **/
    public List<OmniTransactionRes> listOmniTransactions(@Nullable String address) {

        AssertUp.isTrue(validateAddress(address).isvalid || address == null || address.equals("*"), E.ADDRESS_ERROR);

        String x = StringUtils.isEmpty(address) ? "*" : address;
        return http.engine("omni_listtransactions",
                List.class,
                x,
                100,
                0,
                0,
                999999999);
    }

    /***
     * 列出【本机】钱包中未验证的事务
     * @param: []
     * @return: void
     **/
    public List<OmniTransactionRes> listOmniPendingTransactions() {
        return http.engine("omni_listpendingtransactions", List.class);
    }

    /***
     * 获取【全网】下固定区块高度的事务列表
     * @param: [block_height：区块高度]
     * @return: 哈希列表
     **/
    public List<String> listBlockTransactions(long block_height) {
        return http.engine("omni_listblocktransactions", List.class, block_height);
    }

    /***
     * 钱包数据备份
     * @param: []
     * @return: void
     * https://bitcoin.org/en/developer-reference#backupwallet
     **/
    public String backupWallet() {
        System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String file = String.format("wallet-%s.dat", time);
        http.engine("backupwallet", file);
        return file;
    }

    /***
     * 钱包数据导入
     * @param: []
     * @return: void
     * https://bitcoin.org/en/developer-reference#importwallet
     *对于影响新添加的密钥的事务，此调用可能需要重新扫描整个或部分块链，这可能需要几分钟。
     **/
    public void importWallet(String fielName) {
        http.engine("importwallet", fielName);
    }

    /***
     * 导出钱包数据以人类可读的方式
     * @param: []
     * @return: java.lang.String
     **/
    public String dumpWallet() {
        System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String file = String.format("wallet-%s.txt", time);
        http.engine("dumpwallet", file);
        return file;
    }




}
