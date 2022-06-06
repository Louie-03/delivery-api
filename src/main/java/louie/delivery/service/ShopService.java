package louie.delivery.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import louie.delivery.domain.Shop;
import louie.delivery.domain.ShopCategory;
import louie.delivery.dto.ShopDetailResponse;
import louie.delivery.dto.ShopResponse;
import louie.delivery.repository.ShopCategoryRepository;
import louie.delivery.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopService {

	private final ShopRepository shopRepository;
	private final ShopCategoryRepository shopCategoryRepository;

	public ShopDetailResponse findById(Long id) {
		Shop shop = shopRepository.findWithItemById(id)
			.orElseThrow(() -> new NoSuchElementException("가게를 찾을 수 없습니다."));
		return new ShopDetailResponse(shop);
	}

	public List<ShopResponse> searchByShopNameAndContainsShopCategory(String shopName, Long shopCategoryId) {
		if (ObjectUtils.isEmpty(shopName) && ObjectUtils.isEmpty(shopCategoryId)) {
			return shopRepository.findAll().stream()
				.map(ShopResponse::new)
				.collect(Collectors.toList());
		}

		if (ObjectUtils.isEmpty(shopCategoryId)) {
			return shopRepository.findByNameContains(shopName).stream()
				.map(ShopResponse::new)
				.collect(Collectors.toList());
		}

		ShopCategory shopCategory = shopCategoryRepository.findWithShopById(shopCategoryId)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 카테고리입니다."));

		return shopCategory.getShopCategoryDetails().stream()
			.map(shopCategoryDetail -> new ShopResponse(shopCategoryDetail.getShop()))
			.collect(Collectors.toList());
	}
}
