package louie.delivery.dto;

import lombok.Getter;
import louie.delivery.domain.ShopCategory;

@Getter
public class ShopCategoryResponse {

	private Long id;
	private String name;

	public ShopCategoryResponse(ShopCategory shopCategory) {
		this.id = shopCategory.getId();
		this.name = shopCategory.getName();
	}
}
