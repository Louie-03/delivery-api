package louie.delivery.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.delivery.dto.ShopCategoryResponse;
import louie.delivery.service.ShopCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-categories")
@RequiredArgsConstructor
public class ShopCategoryController {

    private final ShopCategoryService shopCategoryService;

    @GetMapping
    public List<ShopCategoryResponse> getShopCategories() {
        return shopCategoryService.findAll();
    }

}
