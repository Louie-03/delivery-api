package louie.delivery.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import louie.delivery.domain.Shop;

@Getter
public class ShopDetailResponse {

	private Long id;
	private String name;
	private List<ItemCategoryResponse> itemCategories;

	public ShopDetailResponse(Shop shop) {
		this.id = shop.getId();
		this.name = shop.getName();
		this.itemCategories = shop.getItemCategories().stream()
			.map(ItemCategoryResponse::new)
			.collect(Collectors.toList());
	}
}
