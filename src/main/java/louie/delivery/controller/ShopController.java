package louie.delivery.controller;

import lombok.RequiredArgsConstructor;
import louie.delivery.dto.ShopDetailResponse;
import louie.delivery.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/shops")
@RestController
public class ShopController {

	private final ShopService shopService;

	@GetMapping("/{id}")
	public ShopDetailResponse getShopDetail(@PathVariable Long id) {
		return shopService.findById(id);
	}
}
