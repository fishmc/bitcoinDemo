package com.lanjing.bitcoin.dto.response;


import lombok.Data;

@Data
public class Alerts {

    private String alerttype;
    private String alertexpiry;
    private String alertmessage;
}
