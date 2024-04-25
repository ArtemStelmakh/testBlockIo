package imp;

import api.transaction.Transaction;
import api.transaction.TxsItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import service.TransactionServ;

import java.io.IOException;
import java.util.List;

public class TransactionImpl implements TransactionServ {
    @Override
    public List<TxsItem> getListTransaction(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<TxsItem> getList;
        try {
            getList = objectMapper.readValue(jsonObject.toJSONString(), Transaction.class).getData().getTxs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getList;
    }

    @Override
    public String getStatusTransaction(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        String status;
        try {
            status = objectMapper.readValue(jsonObject.toJSONString(), Transaction.class).getStatus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public void doSomeTransaction(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        ToDO: Here need to implement some custom wait until the transaction is finished
    }

}
