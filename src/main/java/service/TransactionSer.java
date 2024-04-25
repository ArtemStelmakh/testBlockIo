package service;

import api.transaction.TxsItem;
import org.json.simple.JSONObject;

import java.util.List;

public interface TransactionSer {


    List<TxsItem> getListTransaction(JSONObject jsonObject);
 }
