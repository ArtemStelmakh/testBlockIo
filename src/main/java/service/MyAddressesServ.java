package service;

import org.json.simple.JSONObject;

import java.util.List;

public interface MyAddressesServ {

    List<String> getAllAddressesLabel(JSONObject jsonObject);

    double getAvailableBalanceForLabel(JSONObject jsonObject);
}
