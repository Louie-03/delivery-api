package louie.delivery.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import louie.delivery.dto.ShopCategoryResponse;
import louie.delivery.repository.ShopCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopCategoryService {

	private final ShopCategoryRepository shopCategoryRepository;

	public List<ShopCategoryResponse> findAll() {
		return shopCategoryRepository.findAll().stream()
			.map(ShopCategoryResponse::new)
			.collect(Collectors.toList());
	}

}
