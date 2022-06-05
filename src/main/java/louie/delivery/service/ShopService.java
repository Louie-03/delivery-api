package louie.delivery.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import louie.delivery.domain.Shop;
import louie.delivery.dto.ShopDetailResponse;
import louie.delivery.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopService {

	private final ShopRepository shopRepository;

	public ShopDetailResponse findById(Long id) {
		Shop shop = shopRepository.findWithItemById(id)
			.orElseThrow(() -> new NoSuchElementException("가게를 찾을 수 없습니다."));
		return new ShopDetailResponse(shop);
	}
}
