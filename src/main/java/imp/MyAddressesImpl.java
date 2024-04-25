package imp;

import api.addresses.AddressesItem;
import api.addresses.AllMyAddresses;
import api.addressesByLeabel.MyAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import service.MyAddressesServ;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MyAddressesImpl implements MyAddressesServ {
    @Override
    public List<String> getAllAddressesLabel(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> getAddressesLabel;
        List<AddressesItem> address;
        try {
            address = objectMapper.readValue(jsonObject.toJSONString(), AllMyAddresses.class).getData().getAddresses();
            getAddressesLabel = address.stream().map(AddressesItem::getLabel).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getAddressesLabel;
    }

    @Override
    public double getAvailableBalanceForLabel(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        String balance;
        try {
            balance = objectMapper.readValue(jsonObject.toJSONString(), MyAddress.class).getData().getAvailableBalance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Double.parseDouble(balance);
    }
}
