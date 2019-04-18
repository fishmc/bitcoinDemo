package com.lanjing.bitcoin.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "Transaction",description = "交易请求对象")
public class Transaction {

    @ApiModelProperty(value = "发送方",required = true)
    private String fromaddress;
    @ApiModelProperty(value = "接收方",required = true)
    private String toaddress;
    @ApiModelProperty(value = "代币id",required = true)
    private int propertyid;
    @ApiModelProperty(value = "金额",required = true)
    private double amount;
    @ApiModelProperty(value = "手续费地址",required = true)
    private String feeaddress;

    public Transaction(String fromaddress, String toaddress, int propertyid, double amount, String feeaddress) {
        this.fromaddress = fromaddress;
        this.toaddress = toaddress;
        this.propertyid = propertyid;
        this.amount = amount;
        this.feeaddress = feeaddress;
    }

    public Transaction() {
    }
}
