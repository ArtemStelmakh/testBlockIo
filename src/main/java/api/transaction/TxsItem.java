package api.transaction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TxsItem{

	@JsonProperty("from_green_address")
	private boolean fromGreenAddress;

	@JsonProperty("amounts_received")
	private List<AmountsReceivedItem> amountsReceived;

	private double confidence;

	private String txid;

	private int time;

	private int confirmations;

	@JsonProperty("propagated_by_nodes")
	private Object propagatedByNodes;

	private List<String> senders;
}