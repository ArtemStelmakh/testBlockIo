package api.addresses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data{
	private List<AddressesItem> addresses;
	private int page;
	@JsonProperty("has_more")
	private boolean hasMore;
	private String network;
}