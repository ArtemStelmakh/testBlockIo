package imp;

import api.transaction.Transaction;
import api.transaction.TxsItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import service.TransactionSer;

import java.io.IOException;
import java.util.List;

public class TransactionImpl implements TransactionSer {
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
}
