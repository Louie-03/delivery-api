package louie.delivery.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShopCategoryDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY, cascade = PERSIST)
	@JoinColumn
	private Shop shop;

	@ManyToOne(fetch = LAZY, cascade = PERSIST)
	@JoinColumn
	private ShopCategory shopCategory;

}
