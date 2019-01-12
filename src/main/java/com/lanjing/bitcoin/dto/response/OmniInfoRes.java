package com.lanjing.bitcoin.dto.response;

import lombok.Data;

import java.util.List;


@Data
public class OmniInfoRes {


    /**
     * Result:
     * {
     *   "omnicoreversion_int" : xxxxxxx,      // (number) client version as integer
     *   "omnicoreversion" : "x.x.x.x-xxx",    // (string) client version
     *   "mastercoreversion" : "x.x.x.x-xxx",  // (string) client version (DEPRECIATED)
     *   "bitcoincoreversion" : "x.x.x",       // (string) Bitcoin Core version
     *   "commitinfo" : "xxxxxxx",             // (string) build commit identifier
     *   "block" : nnnnnn,                     // (number) index of the last processed block
     *   "blocktime" : nnnnnnnnnn,             // (number) timestamp of the last processed block
     *   "blocktransactions" : nnnn,           // (number) Omni transactions found in the last processed block
     *   "totaltransactions" : nnnnnnnn,       // (number) Omni transactions processed in total
     *   "alerts" : [                          // (array of JSON objects) active protocol alert (if any)
     *     {
     *       "alerttype" : n                       // (number) alert type as integer
     *       "alerttype" : "xxx"                   // (string) alert type (can be "alertexpiringbyblock", "alertexpiringbyblocktime", "alertexpiringbyclientversion" or "error")
     *       "alertexpiry" : "nnnnnnnnnn"          // (string) expiration criteria (can refer to block height, timestamp or client verion)
     *       "alertmessage" : "xxx"                // (string) information about the alert
     *     },
     *     ...
     *   ]
     * }
     * */

    private String mastercoreversion;
    private int totaltransactions;
    private int blocktime;
    private int blocktransactions;
    private int totaltrades;
    private String omnicoreversion;
    private String bitcoincoreversion;
    private int omnicoreversion_int;
    private int block;
     private List<Alerts> alerts;

}
