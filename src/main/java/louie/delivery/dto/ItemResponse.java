package louie.delivery.dto;

import lombok.Getter;
import louie.delivery.domain.Item;

@Getter
public class ItemResponse {

	private Long id;
	private String name;
	private int price;

	public ItemResponse(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
	}
}
