import api.transaction.TxsItem;
import com.jayway.jsonpath.JsonPath;
import imp.MyAddressesImpl;
import imp.TransactionImpl;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lib.blockIo.BlockIo;
import readProperties.ReadConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BloclIoTest {
    BlockIo blockLib;
    private String label = "test2";

    @BeforeEach
    public void setUp(){
        String keyBitcoin = ReadConfig.readProperties("apiKeyBitCoin");
        try {
            if(blockLib==null){
                blockLib = new BlockIo(keyBitcoin, "CWx5kVINS9U1Y3Wi97U8");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validateCreateNewAddressTest(){
        JSONObject object = new JSONObject();
        object.put("label",label);
        try {
//            blockLib.GetNewAddress(object);
            JSONObject response = blockLib.GetMyAddresses(null);
            System.out.println(response);
            MyAddressesImpl myAddresses = new MyAddressesImpl();
            List<String> addresses = myAddresses.getAllAddressesLabel(response);
            assertTrue(addresses.contains(label), String.format("New address is not created with label: %s", label));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void validateAddressBalanceTest(){
        double updatedBalanceValue = 0.005;
        MyAddressesImpl myAddresses = new MyAddressesImpl();
        TransactionImpl transaction = new TransactionImpl();
        JSONObject response;
        JSONObject object = new JSONObject();
        object.put("label",label);
        try {
            response = blockLib.GetAddressByLabel(object);
            double getBalanceBefore = myAddresses.getAvailableBalanceForLabel(response);
            System.out.println(response);
            transaction.doSomeTransaction();
            response = blockLib.GetAddressByLabel(object);
            double getBalanceAfter = myAddresses.getAvailableBalanceForLabel(response);
            System.out.println(response);
            assertEquals(getBalanceAfter+updatedBalanceValue, getBalanceBefore, "Balance is not updated");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void validatePrepareTransaction(){
//        JSONObject preparedTransaction;
//        JSONObject transactionData;
//
//        JSONObject object = new JSONObject();
//        object.put("amounts","0.00001");
//        object.put("from_labels",label);
//        object.put("to_labels","test2");
//
//
//
//        try {
//            preparedTransaction = blockLib.PrepareTransaction(object);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("Prepared Transaction: " + preparedTransaction.toJSONString());
//
//        // summarize the prepared transaction
//        // inspect this in-depth yourself, we're just showing the summary here
//        System.out.println("Summarized Prepared Transaction: " + blockLib.SummarizePreparedTransaction(preparedTransaction));
//
//        // create and sign the prepared transaction
//        // transactionData contains the unsigned tx_hex (inspect it yourself), and your signatures to append to the transaction
//        try {
//            transactionData = blockLib.CreateAndSignTransaction(preparedTransaction);
//
//            System.out.println("Transaction Data: " + transactionData.toJSONString());
//            JSONObject objectTransactionData = new JSONObject();
//            objectTransactionData.put("transaction_data",transactionData);
//
//            // submit the transaction
//            // if partially signed, Block.io will add its signature to complete the transaction
//            // and then broadcast to the peer-to-peer blockchain network
//            System.out.println("Submit Transaction: " + blockLib.SubmitTransaction(objectTransactionData));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void validateGetTransactionsTest(){
        TransactionImpl transactionImpl = new TransactionImpl();
        JSONObject object = new JSONObject();
        object.put("type","received");
        try {
            JSONObject response = blockLib.GetTransactions(object);
            List<TxsItem> getTransaction = transactionImpl.getListTransaction(response);
            String getStatus = transactionImpl.getStatusTransaction(response);
            assertTrue(!getTransaction.isEmpty() && getStatus.equals("success"), "User is not get response transactions");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
