package service;

import api.transaction.TxsItem;
import org.json.simple.JSONObject;

import java.util.List;

public interface TransactionServ {


    List<TxsItem> getListTransaction(JSONObject jsonObject);

    String getStatusTransaction(JSONObject jsonObject);
 }
