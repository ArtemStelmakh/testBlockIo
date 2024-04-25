package api.addressesByLeabel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data{
	private String address;
	@JsonProperty("user_id")
	private int userId;
	private String label;
	@JsonProperty("available_balance")
	private String availableBalance;
	@JsonProperty("pending_received_balance")
	private String pendingReceivedBalance;
	private String network;
}