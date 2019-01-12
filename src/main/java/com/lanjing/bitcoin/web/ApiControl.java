package com.lanjing.bitcoin.web;

import com.lanjing.bitcoin.dao.BitcoinDao;
import com.lanjing.bitcoin.dto.response.*;
import com.lanjing.bitcoin.exception.E;
import com.lanjing.bitcoin.utils.AssertUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiControl {


    @Autowired
    BitcoinDao bitcoinDao;

    @RequestMapping(value = "/getinfo",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BitCoinInfoRes getBitCoinInfo(){
        return bitcoinDao.getBitCoinInfo();
    }


    @RequestMapping(value = "/validate/{address}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean validateAddress(@PathVariable String address) {
        return bitcoinDao.validateAddress(address).isIsvalid();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNewAddrress() {
        String getnewaddress = bitcoinDao.getnewaddress(null);
        System.out.println(getnewaddress);
        return getnewaddress;
    }

    @RequestMapping(value = "/account/{address}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String[] getAddressesByAccName(@PathVariable String accountName){
        return bitcoinDao.getAddressesByAccName(accountName);
    }

    @RequestMapping(value = "/getAccount/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAccountByAddress(@PathVariable String address){
        return bitcoinDao.getAccountByAddress(address);
    }

    @RequestMapping(value = "/getBalance/{account}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBalance(@PathVariable String account){
        return bitcoinDao.getBalance(account);
    }


    @RequestMapping(value = "/getReceived/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getReceivedByAddress(@PathVariable String address) {
        return bitcoinDao.getReceivedByAddress(address);
    }

    @RequestMapping(value = "/listUnSpent/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UnspentRes> listUnSpent(@PathVariable String address){
        return bitcoinDao.listUnSpent(address);
    }


    @RequestMapping(value = "/listReceivedByAddress",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BtcAddressInfo>  listReceivedByAddress(){
        return bitcoinDao.listReceivedByAddress();
    }

    /****************************USDT**************************/

    @RequestMapping(value = "/getOmniPropertyById/{propertyid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OmniPropertyInfoRes getOmniPropertyById(@PathVariable int propertyid) {
        return bitcoinDao.getOmniPropertyById(propertyid);
    }

    @RequestMapping(value = "/getBalanceByAddAndId",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTokenBalanceInfoRes> getBalanceByAddAndId(@PathVariable String address, @PathVariable int propertyid) {
       return bitcoinDao.getBalanceByAddAndId(address,propertyid);
    }

    @RequestMapping(value = "/getOmniBalanceByAddress/{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTokenBalanceInfoRes> getOmniBalanceByAddress(@PathVariable String address) {
        return bitcoinDao.getOmniBalanceByAddress(address);
    }


    @RequestMapping(value = "/getAllBalancesWithAddress",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTokenBalWithAddressRes> getAllBalancesWithAddress() {
        return bitcoinDao.getAllBalancesWithAddress();
    }

    @RequestMapping(value = "/getAllBalances",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniTokenBalanceInfoRes> getAllBalances(){
        return bitcoinDao.getAllBalances();
    }

    @RequestMapping(value = "/sendToken",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendToken(@PathVariable String fromaddress,
                            @PathVariable String toaddress,
                            @PathVariable int propertyid,
                            @PathVariable double amount,
                            @PathVariable String feeaddress) {
        return bitcoinDao.sendOmniToken(fromaddress,toaddress,propertyid,amount,feeaddress);
    }


    @RequestMapping(value = "/getOmniTransaction/{txid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OmniTransactionRes getOmniTransaction(@PathVariable String txid) {
        return bitcoinDao.getOmniTransaction(txid);
    }

    @RequestMapping(value = "/listBlockTransactions/{block_height}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> listBlockTransactions(@PathVariable long block_height) {
        return bitcoinDao.listBlockTransactions(block_height);
    }


    @RequestMapping(value = "/backupWallet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String backupWallet() {
        return bitcoinDao.backupWallet();
    }

    @RequestMapping(value = "/importWallet",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String importWallet(@PathVariable String fielName){
        bitcoinDao.importWallet(fielName);
        return "success";
    }

    @RequestMapping(value = "/dumpWallet",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String dumpWallet() {
        return bitcoinDao.dumpWallet();
    }


    @RequestMapping(value = "/bitcoinSpent",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String bitcoinSpent(@PathVariable String address, @PathVariable double amount) throws Exception {
        return bitcoinDao.bitcoinSpent(address,amount);
    }


    @RequestMapping(value = "/getListProperties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OmniPropertyInfoRes> getOmniListProperties() {
        return bitcoinDao.getOmniListProperties();
    }














}
