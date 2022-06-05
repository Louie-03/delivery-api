package louie.delivery.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import louie.delivery.domain.ItemCategory;

@Getter
public class ItemCategoryResponse {

	private String name;
	private List<ItemResponse> items;

	public ItemCategoryResponse(ItemCategory itemCategory) {
		this.name = itemCategory.getName();
		this.items = itemCategory.getItemCategoryDetails().stream()
			.map(itemCategoryDetail -> new ItemResponse(itemCategoryDetail.getItem()))
			.collect(Collectors.toList());
	}
}
