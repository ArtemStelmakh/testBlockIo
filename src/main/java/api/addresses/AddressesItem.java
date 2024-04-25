package api.addresses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesItem{
	private String address;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("is_segwit")
	private boolean isSegwit;

	private String label;

	@JsonProperty("available_balance")
	private String availableBalance;

	@JsonProperty("pending_received_balance")
	private String pendingReceivedBalance;

}