package com.lanjing.bitcoin.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Signrawtransaction {


    /**
     * {
     *   "hex": "0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c20000000000ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee0200000000ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000",
     *   "complete": false,
     *   "errors": [
     *     {
     *       "txid": "c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",
     *       "vout": 0,
     *       "scriptSig": "",
     *       "sequence": 4294967295,
     *       "error": "Input not found or already spent"
     *     },
     *     {
     *       "txid": "ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",
     *       "vout": 2,
     *       "scriptSig": "",
     *       "sequence": 4294967295,
     *       "error": "Input not found or already spent"
     *     }
     *   ]
     * }
     * */
    public String hex;
    public boolean complete;
    public List<Errors> errors;
}
