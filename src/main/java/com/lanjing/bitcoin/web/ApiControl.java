package com.lanjing.bitcoin.web;

import com.lanjing.bitcoin.dao.BitcoinDao;
import com.lanjing.bitcoin.dto.request.Payload;
import com.lanjing.bitcoin.dto.request.Trans;
import com.lanjing.bitcoin.dto.request.Transaction;
import com.lanjing.bitcoin.dto.request.User;
import com.lanjing.bitcoin.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "usdt操作api", description = "usdt操作api", tags = "USDTApi", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiControl {


    @Autowired
    BitcoinDao bitcoinDao;


    @ApiOperation(value = "/val", notes = "校验地址")
    @ApiImplicitParam(name = "address", value = "校验地址", required = true, dataType = "String")
    @RequestMapping(value = "/val/{address}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean validateAddress(@PathVariable String address) {
        return bitcoinDao.validateAddress(address).isIsvalid();
    }


    @ApiOperation(value = "/create", notes = "通过节点创建新地址")
    @ApiImplicitParam(name = "account", value = "账号名称", required = true, dataType = "String")
    @RequestMapping(value = "/create/{account}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNewAddrress(@PathVariable String account) {
        String getnewaddress = bitcoinDao.getnewaddress(account);
        System.out.println(getnewaddress);
        return getnewaddress;
    }

    @ApiOperation(value = "/listAddr", notes = "获取账户名称下的地址列表")
    @ApiImplicitParam(name = "account", value = "账户名称", required = true, dataType = "String")
    @RequestMapping(value = "/listAddr/{account}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String[] getAddressesByAccName(@PathVariable String account){
        return bitcoinDao.getAddressesByAccName(account);
    }

    @ApiOperation(value = "/account", notes = "获取地址对于的账户名称 by address")
    @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String")
    @RequestMapping(value = "/account/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAccountByAddress(@PathVariable String address){
        return bitcoinDao.getAccountByAddress(address);
    }



    @ApiOperation(value = "/balance",notes = "获取余额信息")
    @ApiImplicitParam(name = "account", value = "查询账户", required = true, dataType = "String")
    @RequestMapping(value = "/balance/{account}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBalance(@PathVariable String account){
        return bitcoinDao.getBalance(account);
    }


    @ApiOperation(value = "/received",notes = "查询指定地址的收款总额（不是余额！不是余额！不是余额")
    @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String")
    @RequestMapping(value = "/received/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getReceivedByAddress(@PathVariable String address) {
        return bitcoinDao.getReceivedByAddress(address);
    }

//    @ApiOperation(value = "/listutxo", notes = "查询utxo列表")
//    @ApiImplicitParam(name = "address", value = "通过address查询UTXO", required = true, dataType = "List<UnspentRes>")
//    @RequestMapping(value = "/listutxo/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<UnspentRes> listUnSpent(@PathVariable String address){
//        return bitcoinDao.listUnSpent(address);
//    }



    @ApiOperation(value = "/listwallet", notes = "获取钱包内比特币地址信息列表")
    @RequestMapping(value = "/listwallet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BtcAddressInfo>  listReceivedByAddress(){
        return bitcoinDao.listReceivedByAddress();
    }

    @ApiOperation(value = "/listtoken", notes = "列出所有令牌或智能属性。")
    @RequestMapping(value = "/listtoken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniPropertyInfoRes> getOmniListProperties() {
        return bitcoinDao.getOmniListProperties();
    }


    @ApiOperation(value = "/token", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "propertyid", value = "代币ID", required = true, dataType = "Integer", defaultValue = "31")
    @RequestMapping(value = "/token/{propertyid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OmniPropertyInfoRes getOmniPropertyById(@PathVariable int propertyid) {
        return bitcoinDao.getOmniPropertyById(propertyid);
    }

    @ApiOperation(value = "/token", notes = "查询令牌余额信息 by 地址&id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "propertyid", value = "代币ID", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/token/{propertyid}/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OmniTokenBalanceInfoRes getBalanceByAddAndId(@PathVariable String address, @PathVariable int propertyid) {
       return bitcoinDao.getBalanceByAddAndId(address,propertyid);
    }


    @ApiOperation(value = "sendtoken", notes = "创建一个简单的交易对象")
    @ApiImplicitParam(name = "Transaction", value = "交易对象", required = true, dataType = "Transaction")
    @RequestMapping(value = "sendtoken",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendToken(@RequestBody Transaction trans){
        return bitcoinDao.sendOmniToken(trans.getFromaddress(),trans.getToaddress(),trans.getPropertyid()
                                ,trans.getAmount(),trans.getFeeaddress());
    }



//
//    @ApiOperation(value = "simplesend", notes = "创建一个简单的交易对象,传统Api")
//    @ApiImplicitParam(name = "Transaction", value = "交易对象", required = true, dataType = "Transaction")
//    @RequestMapping(value = "simplesend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String simplesend(@RequestBody Trans trans){
//        String message;
//        try {
//            return bitcoinDao.Simple_Send_transaction(trans.getFromAddress(),trans.getToAddress(),
//                    trans.getPropertyid(),trans.getAmount(),trans.getFee());
//        } catch (Exception e) {
//            message = e.getMessage();
//            e.printStackTrace();
//        }
//        return message;
//    }


    @ApiOperation(value = "/txid", notes = "查询txid信息")
    @ApiImplicitParam(name = "txid", value = "txid", required = true, dataType = "String")
    @RequestMapping(value = "/txid/{txid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OmniTransactionRes getOmniTransaction(@PathVariable String txid) {
        return bitcoinDao.getOmniTransaction(txid);
    }


    @ApiOperation(value = "/block", notes = "查询区块信息")
    @ApiImplicitParam(name = "block_height", value = "block_height", required = true, dataType = "Long")
    @RequestMapping(value = "/block/{block_height}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> listBlockTransactions(@PathVariable Long block_height) {
        return bitcoinDao.listBlockTransactions(block_height);
    }


    @ApiOperation(value = "/pending", notes = "列出【本机】钱包中未验证的事务")
    @RequestMapping(value = "/pending",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTransactionRes> listOmniPendingTransactions() {
        return bitcoinDao.listOmniPendingTransactions();
    }


    @ApiOperation(value = "/listtrans", notes = "查询区块信息")
    @ApiImplicitParam(name = "address", value = "block_height", required = true, dataType = "Long")
    @RequestMapping(value = "/listtrans/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTransactionRes> listOmniTransactions(@PathVariable String address) {
        return bitcoinDao.listOmniTransactions(address);
    }



    /****************************USDT**************************/



    @ApiOperation(value = "/getOmniBalanceByAddress", notes = "查询令牌余额信息 by 地址")
    @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String")
    @RequestMapping(value = "/getOmniBalanceByAddress/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTokenBalanceInfoRes> getOmniBalanceByAddress(@PathVariable String address) {
        return bitcoinDao.getOmniBalanceByAddress(address);
    }




    @ApiOperation(value = "/sendToken", notes = "新的api.创建并发送资助的简单发送交易。来自发件人的所有比特币都被消费，如果缺少比特币，则从指定的费用来源获取。更改将发送到费用来源！")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromaddress", value = "发送地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "toaddress", value = "接收地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "propertyid", value = "代币ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "feeaddress", value = "支付手续费的地址", required = true, dataType = "String")
    })
    @RequestMapping(value = "/sendToken",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendToken(@PathVariable String fromaddress,
                            @PathVariable String toaddress,
                            @PathVariable int propertyid,
                            @PathVariable double amount,
                            @PathVariable String feeaddress) {
        return bitcoinDao.sendOmniToken(fromaddress,toaddress,propertyid,amount,feeaddress);
    }


    @ApiOperation(value = "/listBlockTransactions", notes = "获取【全网】下固定区块高度的事务列表")
    @ApiImplicitParam(name = "block_height", value = "区块高度", required = true, dataType = "Long")
    @RequestMapping(value = "/listBlockTransactions/{block_height}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> listBlockTransactions(@PathVariable long block_height) {
        return bitcoinDao.listBlockTransactions(block_height);
    }


    @ApiOperation(value = "/backupWallet", notes = "钱包数据备份")
    @RequestMapping(value = "/backupWallet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String backupWallet() {
        return bitcoinDao.backupWallet();
    }


    @ApiOperation(value = "/importWallet", notes = "钱包数据导入")
    @ApiImplicitParam(name = "fielName", value = "钱包地址", required = true, dataType = "String")
    @RequestMapping(value = "/importWallet",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String importWallet(@PathVariable String fielName){
        bitcoinDao.importWallet(fielName);
        return "success";
    }

    @ApiOperation(value = "/dumpWallet", notes = "导出钱包数据以人类可读的方式")
    @RequestMapping(value = "/dumpWallet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String dumpWallet() {
        return bitcoinDao.dumpWallet();
    }


//    @ApiOperation(value = "/bitcoinSpent", notes = "交易方式来至汇智网")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "address", value = "接收地址", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "Double")
//    })
//    @RequestMapping(value = "/bitcoinSpent",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String bitcoinSpent(@PathVariable String address, @PathVariable double amount) throws Exception {
//        return bitcoinDao.bitcoinSpent(address,amount);
//    }















}
