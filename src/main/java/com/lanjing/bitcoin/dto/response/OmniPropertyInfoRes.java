package com.lanjing.bitcoin.dto.response;

import lombok.Data;

@Data
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

    private int propertyid;
    private String name;
    private String category;
    private String subcategory;
    private String data;
    private String url;
    private boolean divisible;
    private String issuer;
    private String creationtxid;
    private boolean fixedissuance;
    private boolean managedissuance;
    private boolean freezingenabled;
    private String totaltokens;
}
