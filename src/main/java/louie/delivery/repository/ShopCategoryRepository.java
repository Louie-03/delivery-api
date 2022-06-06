package louie.delivery.repository;

import java.util.Optional;
import louie.delivery.domain.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Long> {

	@Query("select sc from ShopCategory sc "
		+ "join fetch sc.shopCategoryDetails scd "
		+ "join fetch scd.shop s "
		+ "where sc.id = :id")
	Optional<ShopCategory> findWithShopById(@Param("id") Long id);
}
