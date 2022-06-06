package louie.delivery.dto;

import lombok.Getter;
import louie.delivery.domain.Shop;

@Getter
public class ShopResponse {

	private Long id;
	private String name;

	public ShopResponse(Shop shop) {
		this.id = shop.getId();
		this.name = shop.getName();
	}
}
