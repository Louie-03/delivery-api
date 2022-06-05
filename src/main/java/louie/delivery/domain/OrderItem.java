package louie.delivery.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn
    private Order order;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn
    private Item item;

    private int orderPrice;
    private int count;

}
