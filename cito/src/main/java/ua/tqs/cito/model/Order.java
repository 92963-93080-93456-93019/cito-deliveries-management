package ua.tqs.cito.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @ElementCollection
    private Map<Product, Integer> productsList;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "consumerId") // A Consumer has many orders (foreign key)
    private Consumer endConsumer;

}
