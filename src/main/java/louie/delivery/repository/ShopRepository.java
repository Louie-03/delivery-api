package louie.delivery.repository;

import java.util.Optional;
import louie.delivery.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	@Query("select distinct s from Shop s "
		+ "join fetch s.itemCategories ic "
		+ "where s.id = :id ")
	Optional<Shop> findWithItemById(@Param("id") Long id);
}
