package dao;

import com.lanjing.bitcoin.BitcoinApplication;
import com.lanjing.bitcoin.dao.BitcoinDao;
import com.lanjing.bitcoin.dto.response.Signrawtransaction;
import com.lanjing.bitcoin.dto.response.UnspentRes;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import springfox.documentation.staticdocs.SwaggerResultHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitcoinApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
@Slf4j
public class BitcoinDaoTest {



//    private String snippetDir = "target/generated-snippets";
//    private String outputDir  = "target/asciidoc";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @After
//    public void Test() throws Exception {
//        // 得到swagger.json,写入outputDir目录中
//        mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
//                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
//        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
//        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
//                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
//                .withExamples(snippetDir)
//                .build()
//                .intoFolder(outputDir);// 输出
//    }
//
//    @Test
//    public void TestApi() throws Exception {
//        mockMvc.perform(get("/api/val/1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7")//.param("name", "FLY")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("val", preprocessResponse(prettyPrint())));
//
//
//        mockMvc.perform(get("/api/create/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("create", preprocessResponse(prettyPrint())));
//
//        mockMvc.perform(get("/api/listAddr/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("listAddr", preprocessResponse(prettyPrint())));
//
//        mockMvc.perform(get("/api/account/1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("account", preprocessResponse(prettyPrint())));
//
//
//        mockMvc.perform(get("/api/balance/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("balance", preprocessResponse(prettyPrint())));
//
//        mockMvc.perform(get("/api/balance/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("balance", preprocessResponse(prettyPrint())));
//
//        mockMvc.perform(get("/api/token/31")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("token", preprocessResponse(prettyPrint())));
//
//
//        mockMvc.perform(get("/api/txid/8662e51ae3e9a8a050dc958a95ea27015107388ba212e483755121427e7ba2b7")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("txid", preprocessResponse(prettyPrint())));
//
//        mockMvc.perform(get("/api/txid/8662e51ae3e9a8a050dc958a95ea27015107388ba212e483755121427e7ba2b7")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("txid", preprocessResponse(prettyPrint())));

//
//        Transaction transaction = new Transaction(
//                "1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7",
//                "1G5cdasP7XqZxjn8jCX6UiYKPjjYJP2oUK",31,2.0,
//                "1Mj4gHMQY7YyUBvGKjKttHRa9x1YBsWwGG");
//
//
//        mockMvc.perform(post("/api/sendtoken").contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(transaction))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is5xxServerError())
//                .andDo(MockMvcRestDocumentation.document("sendtoken", preprocessResponse(prettyPrint())));
 //      }



    @Autowired
    BitcoinDao omniCoreDao;

//    //验证地址的合法性
//    @Test
//    public void test() {
//        log.warn(omniCoreDao.validateAddress("1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7").toString());
//    }
//
    //获取新地址
    //1G5cdasP7XqZxjn8jCX6UiYKPjjYJP2oUK
    //lanjing
    @Test
    public void getnewaddress() {
        log.warn(omniCoreDao.getnewaddress("test"));
    }

    //获取账号列表，返回一个String[]
    @Test
    public void getAddressesByAccName() {

        String[] feeaccounts = omniCoreDao.getAddressesByAccName("feeaccount");
        for (String feeaccount : feeaccounts){
            System.out.println(feeaccount);
        }
    }    //获取账号列表，返回一个String[]
//    @Test
//    public void getAddressesByAccName() {
//
//        String[] feeaccounts = omniCoreDao.getAddressesByAccName("feeaccount");
//        for (String feeaccount : feeaccounts){
//            System.out.println(feeaccount);
//        }
//    }
//
    //通过地址获取账号
    @Test
    public void getAccountByAddress() {
        log.warn(omniCoreDao.getAccountByAddress("mua7n818jLniwmP5WMiun18SNDdyEa3zw2"));
    }
//
    //获取制定账号下的余额信息
    @Test
    public void getBalance() {
        log.warn(omniCoreDao.getBalance("feeaccount"));
    }


    //查询账号下的收款总额
    @Test
    public void getReceivedByAddress() {
        log.warn(omniCoreDao.getReceivedByAddress("mua7n818jLniwmP5WMiun18SNDdyEa3zw2"));
    }

    @Test
    public void listReceivedByAddress()  {
        log.warn(String.valueOf(omniCoreDao.listReceivedByAddress()));
    }

    /*******************BitcoinExchange********************/


    //./omnicore-cli listunspent 1 99999999 '''["mua7n818jLniwmP5WMiun18SNDdyEa3zw2"]'''
    // ./omnicore-cli listunspent 1 99999999 '''["1HE66xiaq5ooMADMfRfCXBZApqcW5ZurC5"]''' createrawtransaction
    //    '[{
    //        "txid" : "12b8e7ede4992f4d30f93idj3085746951d945e39f40becebd7c290af8c2e7ad",
    //        "vout" : 0
    //    }]'
    //    '{"mxh3H416KCRoBDiweSESew5YJyAk1nxLrN": 0.025, "1HE66xiaq5ooMADMfRfCXBZApqcW5ZurC5": 0.0245}'
  //  ./omnicore-cli createrawtransaction '[ { "txid" : "245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b", "vout" : 0 }, { "txid" : "ba2647bb6eb71e4e7e152b846485818d2445e83d3173b14a14ccb21736ab616f", "vout" : 1 }, { "txid" : "1a679597b984addf4c67e47c3a49482e691bbbd600b78d132264d23f474c09fd", "vout" : 0 } ]' '{"my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w": 0.025 , "mua7n818jLniwmP5WMiun18SNDdyEa3zw2": 0.0245}'

    //  ./omnicore-cli listunspent 1 99999999 '''["1HE66xiaq5ooMADMfRfCXBZApqcW5ZurC5"]''' signrawtransaction 0100000001e34ac1e2baac09c366fce1c2245536bda8f7db0f6685862aecf53ebd69f9a89c0000000000ffffffff02a0252600000000001976a914d90d36e98f62968d2bc9bbd68107564a156a9bcf88ac50622500000000001976a91407bdb518fa2e6089fd810235cf1100c9c13d1fd288ac00000000

    //  ./omnicore-cli listunspent 1 99999999 '''["1HE66xiaq5ooMADMfRfCXBZApqcW5ZurC5"]''' sendrawtransaction 0100000001e34ac1e2baac09c366fce1c2245536bda8f7db0f6685862aecf53ebd69f9a89c000000006a47304402203e8a16522da80cef66bacfbc0c800c6d52c4a26d1d86a54e0a1b76d661f020c9022010397f00149f2a8fb2bc5bca52f2d7a7f87e3897a273ef54b277e4af52051a06012103c9700559f690c4a9182faa8bed88ad8a0c563777ac1d3f00fd44ea6c71dc5127ffffffff02a0252600000000001976a914d90d36e98f62968d2bc9bbd68107564a156a9bcf88ac50622500000000001976a91407bdb518fa2e6089fd810235cf1100c9c13d1fd288ac00000000




    //列出utxo
//[{txid=245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b, vout=0, address=mua7n818jLniwmP5WMiun18SNDdyEa3zw2, account=feeaccount, scriptPubKey=76a9149a2aecf6a2aa51e6788180c9aaaf0a8a5a0c982788ac, amount=0.08182635, confirmations=14440, spendable=true, solvable=true},
// {txid=1a679597b984addf4c67e47c3a49482e691bbbd600b78d132264d23f474c09fd, vout=0, address=mua7n818jLniwmP5WMiun18SNDdyEa3zw2, account=feeaccount, scriptPubKey=76a9149a2aecf6a2aa51e6788180c9aaaf0a8a5a0c982788ac, amount=0.16878381, confirmations=14250, spendable=true, solvable=true}]
    @Test
    public void listUnSpent() {

        UnspentRes[] utxos = omniCoreDao.listUnSpent("mua7n818jLniwmP5WMiun18SNDdyEa3zw2");

        for (int i = 0; i < utxos.length ; i++) {
            System.out.println(utxos[i].toString());
        }

    }
    //createrawtransaction
    @Test
    public void sentBTC(){
        UnspentRes[] utxos = omniCoreDao.listUnSpent("mua7n818jLniwmP5WMiun18SNDdyEa3zw2");
        String createrawtransaction = omniCoreDao.Createrawtransaction(utxos,"mua7n818jLniwmP5WMiun18SNDdyEa3zw2","my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w",0.05,0.005);
        log.warn(createrawtransaction);
        //0100000003fd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000ffffffff01404b4c00000000001976a914c16ff37ce4420a5e6b42e7c4cadb7b995c64ad4888ac00000000
        //0100000003fd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000ffffffff0000000000
        //01000000011da9283b4ddf8d89eb996988b89ead56cecdc44041ab38bf787f1206cd90b51e0000000000ffffffff01405dc600000000001976a9140dfc8bafc8419853b34d5e072ad37d1a5159f58488ac00000000
        //01000000030b42ff56696c2d4d3c568a1c3b7824cee0f8e058802c07be18b0de39b60e5d240000000000ffffffff6f61ab3617b2cc144ab173313de845248d818564842b157e4e1eb76ebb4726ba0100000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000ffffffff01a0252600000000001976a914c16ff37ce4420a5e6b42e7c4cadb7b995c64ad4888ac00000000
        //0100000003fd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000ffffffff02404b4c00000000001976a914c16ff37ce4420a5e6b42e7c4cadb7b995c64ad4888ac20a10700000000001976a9149a2aecf6a2aa51e6788180c9aaaf0a8a5a0c982788ac00000000
        //0100000001e34ac1e2baac09c366fce1c2245536bda8f7db0f6685862aecf53ebd69f9a89c0000000000ffffffff02a0252600000000001976a914d90d36e98f62968d2bc9bbd68107564a156a9bcf88ac50622500000000001976a91407bdb518fa2e6089fd810235cf1100c9c13d1fd288ac00000000
        //0100000003fd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000fffffffffd094c473fd26422138db700d6bb1b692e48493a7ce4674cdfad84b99795671a0000000000ffffffff02404b4c00000000001976a914c16ff37ce4420a5e6b42e7c4cadb7b995c64ad4888ac20a10700000000001976a9149a2aecf6a2aa51e6788180c9aaaf0a8a5a0c982788ac00000000
    }

    //signrawtransaction
    @Test
    public void signrawtransaction(){
        UnspentRes[] utxos = omniCoreDao.listUnSpent("mua7n818jLniwmP5WMiun18SNDdyEa3zw2");
//        String createrawtransaction = omniCoreDao.Createrawtransaction(utxos,"my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w",0.1);
//        log.warn(omniCoreDao.signrawtransaction(createrawtransaction).toString());
    }

    //sendrawtransaction
    @Test
    public void sendrawtranscation(){
        UnspentRes[] utxos = omniCoreDao.listUnSpent("mua7n818jLniwmP5WMiun18SNDdyEa3zw2");
     String createrawtransaction = omniCoreDao.Createrawtransaction(utxos,"mua7n818jLniwmP5WMiun18SNDdyEa3zw2","my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w",0.05,0.005);
          Signrawtransaction signrawtransaction = omniCoreDao.signrawtransaction(createrawtransaction);
        String sendrawtransaction = omniCoreDao.sendrawtransaction(signrawtransaction.getHex());
       // 16: bad-txns-inputs-duplicate

        log.warn(sendrawtransaction);
    }


//    @Test
//    public void testsignrawtransaction() {
//        UnspentRes utxo1 = new UnspentRes("c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",0,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.001);
//        UnspentRes utxo2 = new UnspentRes("ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",2,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.0083566);
//        List<UnspentRes> UTXOs = new LinkedList<>();
//        UTXOs.add(utxo1);
//        UTXOs.add(utxo2);
//
//        String createrawtransaction = omniCoreDao.Createrawtransaction(UTXOs);
////        String payload = omniCoreDao.payload(2, 0.1);
////        String opreturn = omniCoreDao.omniCreaterawtxOpreturn(createrawtransaction, payload);
////        String reference = omniCoreDao.omniCreaterawtxReference(opreturn, "1Njbpr7EkLA1R8ag8bjRN7oks7nv5wUn3o");
////        String txChange = omniCoreDao.omniCreaterawtxChange(reference
////                , "1K6JtSvrHtyFmxdtGZyZEF7ydytTGqasNc",
////                0.0006, UTXOs);
//        log.warn(omniCoreDao.signrawtransaction("0100000001e34ac1e2baac09c366fce1c2245536bda8f7db0f6685862aecf53ebd69f9a89c0000000000ffffffff02a0252600000000001976a914d90d36e98f62968d2bc9bbd68107564a156a9bcf88ac50622500000000001976a91407bdb518fa2e6089fd810235cf1100c9c13d1fd288ac00000000").getHex());
//    }

    @Test
    public void testSendrawtransaction(){
        String sign = "0100000001e34ac1e2baac09c366fce1c2245536bda8f7db0f6685862aecf53ebd69f9a89c000000006a47304402203e8a16522da80cef66bacfbc0c800c6d52c4a26d1d86a54e0a1b76d661f020c9022010397f00149f2a8fb2bc5bca52f2d7a7f87e3897a273ef54b277e4af52051a06012103c9700559f690c4a9182faa8bed88ad8a0c563777ac1d3f00fd44ea6c71dc5127ffffffff02a0252600000000001976a914d90d36e98f62968d2bc9bbd68107564a156a9bcf88ac50622500000000001976a91407bdb518fa2e6089fd810235cf1100c9c13d1fd288ac00000000";
        log.warn(omniCoreDao.sendrawtransaction(sign));
    }



//    //创建一个 payload
//    @Test
//    public void testpayload(){
//        log.warn(omniCoreDao.payload(2,0.1));
//    }




//    //附加输出交易，step2和step3
//    @Test
//    public void testomniCreaterawtxOpreturn(){
//        UnspentRes utxo1 = new UnspentRes("c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",0);
//        UnspentRes utxo2 = new UnspentRes("ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",0);
//        List<UnspentRes> UTXOs = new LinkedList<>();
//        UTXOs.add(utxo1);
//        UTXOs.add(utxo2);
//        String createrawtransaction = omniCoreDao.Createrawtransaction(UTXOs);
//        String payload = omniCoreDao.payload(2, 0.1);
//        log.warn(omniCoreDao.omniCreaterawtxOpreturn(createrawtransaction,payload));
//    }
    //附加输入交易，step4和输出地址
//    @Test
//    public void testomniCreaterawtxReference(){
//        UnspentRes utxo1 = new UnspentRes("c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",0);
//        UnspentRes utxo2 = new UnspentRes("ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",2);
//        List<UnspentRes> UTXOs = new LinkedList<>();
//        UTXOs.add(utxo1);
//        UTXOs.add(utxo2);
//        String createrawtransaction = omniCoreDao.Createrawtransaction(UTXOs);
//        String payload = omniCoreDao.payload(2, 0.1);
//        String opreturn = omniCoreDao.omniCreaterawtxOpreturn(createrawtransaction, payload);
//        log.warn(omniCoreDao.omniCreaterawtxReference(opreturn,"1Njbpr7EkLA1R8ag8bjRN7oks7nv5wUn3o"));
//    }
//
//    //测试gas
//    @Test
//    public void testomniCreaterawtxChange(){
//        UnspentRes utxo1 = new UnspentRes("c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",0,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.001);
//        UnspentRes utxo2 = new UnspentRes("ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",2,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.0083566);
//        List<UnspentRes> UTXOs = new LinkedList<>();
//        UTXOs.add(utxo1);
//        UTXOs.add(utxo2);
//
//        String createrawtransaction = omniCoreDao.Createrawtransaction(UTXOs);
//        String payload = omniCoreDao.payload(2, 0.1);
//        String opreturn = omniCoreDao.omniCreaterawtxOpreturn(createrawtransaction, payload);
//        String reference = omniCoreDao.omniCreaterawtxReference(opreturn, "1Njbpr7EkLA1R8ag8bjRN7oks7nv5wUn3o");
//        log.warn(omniCoreDao.omniCreaterawtxChange(reference
//                ,"1K6JtSvrHtyFmxdtGZyZEF7ydytTGqasNc",
//                0.0006,UTXOs));
//    }



    //error code: -27
    //error message:
    //transaction already in block chain
    //http://omniexplorer.info/lookuptx.aspx?txid=f404e033d9a8ef815db75d5056eab9f1e09d3865c53afe5ce02884bfb4247047
//    @Test
//    public void testsendrawtransaction() {
//        UnspentRes utxo1 = new UnspentRes("c23495f6e7ba24705d43583edd69ff25a354c18e69fd8514c07ec6f47cb995de",0,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.001);
//        UnspentRes utxo2 = new UnspentRes("ee1673b09b0edaf7aaf8eb0bfd53a5a2757eb3e342e731bfc960b869aa0ab6b3",2,
//                "76a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac",0.0083566);
//        List<UnspentRes> UTXOs = new LinkedList<>();
//        UTXOs.add(utxo1);
//        UTXOs.add(utxo2);
//
//        String createrawtransaction = omniCoreDao.Createrawtransaction(UTXOs);
//        String payload = omniCoreDao.payload(2, 0.1);
//        String opreturn = omniCoreDao.omniCreaterawtxOpreturn(createrawtransaction, payload);
//        String reference = omniCoreDao.omniCreaterawtxReference(opreturn, "1Njbpr7EkLA1R8ag8bjRN7oks7nv5wUn3o");
//        String txChange = omniCoreDao.omniCreaterawtxChange(reference
//                , "1K6JtSvrHtyFmxdtGZyZEF7ydytTGqasNc",
//                0.0006, UTXOs);
//        String hex = omniCoreDao.signrawtransaction(txChange).getHex();
//        //hex->0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c20000000000ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee0200000000ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000
//        //hex->0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c20000000000ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee0200000000ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000
//        //     0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c2000000006a47304402200a6fef16882db2f3e07356b619121d74cf0bd42872cba57430e901b4252f7c8102202edcaa90b278d568faa55a6a9c523ff0cd09e0c916e75ea7baf4690d5747789c01210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee020000006b483045022100ae11d3c92a501496381aa7eaf10ef458f4aabdd3075233ae70d9d32f6b83d812022053aa4171e3d2b58465dde42d07ba4e5f74948b2cdb01a67dfdac4e4eb24b684901210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000
//        log.warn("equals:" + hex.equals("0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c2000000006a47304402200a6fef16882db2f3e07356b619121d74cf0bd42872cba57430e901b4252f7c8102202edcaa90b278d568faa55a6a9c523ff0cd09e0c916e75ea7baf4690d5747789c01210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee020000006b483045022100ae11d3c92a501496381aa7eaf10ef458f4aabdd3075233ae70d9d32f6b83d812022053aa4171e3d2b58465dde42d07ba4e5f74948b2cdb01a67dfdac4e4eb24b684901210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000"));
//        //"code":-25,"message":"Missing inputs"
//        log.warn("hex ----->" + hex);
//        //log.warn(usdt.sendrawtransaction(hex));
//        log.warn(omniCoreDao.sendrawtransaction("0100000002de95b97cf4c67ec01485fd698ec154a325ff69dd3e58435d7024bae7f69534c2000000006a47304402200a6fef16882db2f3e07356b619121d74cf0bd42872cba57430e901b4252f7c8102202edcaa90b278d568faa55a6a9c523ff0cd09e0c916e75ea7baf4690d5747789c01210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffffb3b60aaa69b860c9bf31e742e3b37e75a2a553fd0bebf8aaf7da0e9bb07316ee020000006b483045022100ae11d3c92a501496381aa7eaf10ef458f4aabdd3075233ae70d9d32f6b83d812022053aa4171e3d2b58465dde42d07ba4e5f74948b2cdb01a67dfdac4e4eb24b684901210382df61bad93a1211ceac5c78fd273d65e405a7e148e068ced3e40bf87cf71721ffffffff036a5a0d00000000001976a914c6734676a08e3c6438bd95fa62c57939c988a17b88ac0000000000000000166a146f6d6e690000000000000002000000000098968022020000000000001976a914ee692ea81da1b12d3dd8f53fd504865c9d843f5288ac00000000"));
//    }
//
//    @Test
//    public void testSimple_Send_transaction() throws Exception {
//        log.warn( omniCoreDao.Simple_Send_transaction("my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w",
//                "mktzB7jbVUE3XY7kLxaAgwaBrEyFYoWrNi",2,	1, 0.0006));
//    }
//
    //获取钱包的地址信息
    // - [{address=mg5B7MGjM6A9Px2NdqtTTkJwCSmBFBrNNj, account=test, amount=0.0, confirmations=0, label=test, txids=[]},
    // {address=mktzB7jbVUE3XY7kLxaAgwaBrEyFYoWrNi, account=, amount=5.46E-6, confirmations=13075, label=, txids=[437ee9310cf29ee50c968bd3b0e2245d161b293de8ce5833c001a818122d5f5a]},
    // {address=mqgwkV2ZY5esejpmJ45vM8e76hgswARjcZ, account=test, amount=0.0, confirmations=0, label=test, txids=[]},
    // {address=mua7n818jLniwmP5WMiun18SNDdyEa3zw2, account=feeaccount, amount=1.50526494, confirmations=12853, label=feeaccount, txids=[245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b, 4bec823640e69905feb54f87016853a11745f2e05c1a13e57822425d2b2e3a4c, 437ee9310cf29ee50c968bd3b0e2245d161b293de8ce5833c001a818122d5f5a, 087afbba7cbbe9bc06999ac76994d0ae4f23cbb11021a3b4323774c360480165, 35631e9ec7ecb25f6ebe746e9cccd5f4711aecf6ec83f813749010c742d01b89, c85f877401188865ec5c3b5f7f5283751a0d8b23247125e57637c55a0a9190c1, 4f87be88c99c44967e923930842021964db83a9bb40c96b654faa2b352507ff6, 1a679597b984addf4c67e47c3a49482e691bbbd600b78d132264d23f474c09fd]},
    // {address=my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w, account=, amount=0.61440274, confirmations=12519, label=, txids=[245d0eb639deb018be072c8058e0f8e0ce24783b1c8a563c4d2d6c6956ff420b, e2d7c3188974e4380621e0f47ce2369e9b1062f4d158098b7b32862c3ff29813, 087afbba7cbbe9bc06999ac76994d0ae4f23cbb11021a3b4323774c360480165, 9346e26bfa6202323cab7588079c34ad2c9627aa8a273e1a22386c0b7e0a42a3, 57df9da4e571fd3ea9372177d86e75654e8f3cc0cf8877a311859989c3e4a1be, 83619f162a83fbd19730258d1f63bb4a69595fac5af0fb014415e6e4a33622f7, 29cb65f77979d020c4c6d0d01ad7b2f8e224b059c9485da74f3bf21c2d9012fe]},
    // {address=mzYcc3yEaJwEyKmAvUKMWsJSm5iWigS1BF, account=feeaccount, amount=0.08100226, confirmations=13332, label=feeaccount, txids=[683583544e600f350d861d189b80679ca7039b68bbb7b5e8bfdf10dbe022440d]},
    // {address=n44vjyLwWj2a9t8c1xXWhVsTresFEExnZU, account=test, amount=0.0, confirmations=0, label=test, txids=[]}]
   //
////    //获取钱包的地址信息
////    @Test
////    public void getOmniListProperties()  {
////        log.warn(String.valueOf(omniCoreDao.getOmniListProperties()));
////    }

    //获取钱包信息
    @Test
    public void getOmniInfo()  {
        log.warn(String.valueOf(omniCoreDao.getOmniInfo()));
    }

//
////    //获取代币信息
////    @Test
////    public void getOmniPropertyById()  {
////        log.warn(String.valueOf(omniCoreDao.getOmniPropertyById(31)));
////    }
//
//    //获取地址代币信息
//    @Test
//    public void getBalanceByAddAndId()  {
//        log.warn(String.valueOf(omniCoreDao.getBalanceByAddAndId("mkQMhnQqGysaL89PJ5PHfcYkEBSF8Rn8XX",2)));
//    }
////
////    //获取指定代币信息
////    @Test
////    public void getOmniBalanceById()  {
////        log.warn(String.valueOf(omniCoreDao.getOmniBalanceById(31)));
////    }
//
//
//    //获取地址上的令牌信息
////    @Test
////    public void getOmniBalanceByAddress()  {
////        log.warn(String.valueOf(omniCoreDao.getOmniBalanceByAddress("1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7")));
////    }
//
//    //59b4c7eacf4f06ccad5878a79fc3453d2d75963d3512062a38d73892c67893f0
//    //74913c40cb78c808c43c8d3ae57f3d8bca4f2fb9ea2ed506dc1550b3e2da1149
//    //64d9545585ca4e1850bb89d368658d2cafc477448279032d68e8cdcb9fa98c22
//    //4847886259e7e0e2dffc123d0c0a8bf563d797e9ccc5723498aec587071d92a8
    @Test
    public void sentToAddress(){
        log.warn(omniCoreDao.sendtoaddress("moneyqMan7uh8FqdCA2BV5yZ8qVrc9ikLP",0.1));
    }
//
//    //转账交易令牌，固定手续费地址
//    //437ee9310cf29ee50c968bd3b0e2245d161b293de8ce5833c001a818122d5f5a  my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w
//    //087afbba7cbbe9bc06999ac76994d0ae4f23cbb11021a3b4323774c360480165  mua7n818jLniwmP5WMiun18SNDdyEa3zw2
//    @Test
//    public void sendOmniToken() {
//        log.warn(omniCoreDao.sendOmniToken("mua7n818jLniwmP5WMiun18SNDdyEa3zw2",
//                "my9kpaTvPvoBJsABQR6uzwXPRnEbkRkn8w",
//                2,
//                1,
//                "mua7n818jLniwmP5WMiun18SNDdyEa3zw2"));
////        log.warn(omniCoreDao.sendOmniToken("1Mkj9QEtL4zJR5jzDFxgpekZ8YUfav6TV7",
////                "1G5cdasP7XqZxjn8jCX6UiYKPjjYJP2oUK",
////                31,
////                2.0,
////                "1Mj4gHMQY7YyUBvGKjKttHRa9x1YBsWwGG"));
//    }
//
//
//    @Test
//    public void getOmniTransaction() {
//        log.warn(String.valueOf(omniCoreDao.getOmniTransaction("4f87be88c99c44967e923930842021964db83a9bb40c96b654faa2b352507ff6")));
//    }
////
////    //备份钱包以 .dat的格式
////    @Test
////    public void backupWallet() {
////        log.warn(omniCoreDao.backupWallet());
////    }
////
////    //导入钱包
////    @Test
////    public void importWallet() {
////      omniCoreDao.importWallet("/tmp/wallet.txt");
////    }
////
//    //导出钱包以 .txt的格式
//    @Test
//    public void dumpWallet() {
//        log.warn(omniCoreDao.dumpWallet());
//    }
//
    //获取区块高度
    //8662e51ae3e9a8a050dc958a95ea27015107388ba212e483755121427e7ba2b7
    //8662e51ae3e9a8a050dc958a95ea27015107388ba212e483755121427e7ba2b7
    @Test
    public void listBlockTransactions() {
        log.warn(String.valueOf(omniCoreDao.listBlockTransactions(1453769).size()));
    }
//
////    @Test
////    public void listOmniPendingTransactions() {
////        log.warn(String.valueOf(omniCoreDao.listOmniPendingTransactions().size()));
////    }
////
////    @Test
////    public void listOmniTransactions() {
////        log.warn(String.valueOf(omniCoreDao.listOmniTransactions("1FoWyxwPXuj4C6abqwhjDWdz6D4PZgYRjA")));
////    }
//
//
//
}