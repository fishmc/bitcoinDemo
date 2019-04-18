package com.lanjing.bitcoin.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Transaction",description = "交易对象,对应较老的api")
public class Trans {

    @ApiModelProperty(value = "发送方",required = true)
    private String fromAddress;
    @ApiModelProperty(value = "接受方",required = true)
    private String toAddress;
    @ApiModelProperty(value = "代币id",required = true)
    private int propertyid;
    @ApiModelProperty(value = "金额",required = true)
    private double amount;
    @ApiModelProperty(value = "手续费",required = true)
    private double fee;
}
