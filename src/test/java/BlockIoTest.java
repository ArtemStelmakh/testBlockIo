import api.transaction.TxsItem;
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


public class BlockIoTest {
    BlockIo blockLib;
    private String label = "test2";

    @BeforeEach
    public void setUp(){
        String keyBitcoin = ReadConfig.readProperties("apiKeyBitCoin");
        try {
            if(blockLib==null){
                blockLib = new BlockIo(keyBitcoin);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validateCreateNewAddressTest(){
        String label = "test4";
        JSONObject object = new JSONObject();
        object.put("label",label);
        try {
            blockLib.GetNewAddress(object);
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
            assertEquals(getBalanceAfter + updatedBalanceValue, getBalanceBefore, "Balance is not updated");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validateGetTransactionsTest(){
        TransactionImpl transactionImpl = new TransactionImpl();
        JSONObject object = new JSONObject();
        object.put("type","received");
        try {
            JSONObject response = blockLib.GetTransactions(object);
            List<TxsItem> getTransaction = transactionImpl.getListTransaction(response);
            String getStatus = transactionImpl.getStatusTransaction(response);
            assertTrue(getStatus.equals("success") & !getTransaction.isEmpty(), "User is not get response transactions");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
