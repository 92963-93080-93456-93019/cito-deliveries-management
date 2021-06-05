package ua.tqs.cito.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
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
    private STATUS status;

    @ManyToOne
    @JoinColumn(name = "riderId") // A rider  has many orders (foreign key)
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "appId") // An App has many orders (foreign key)
    private App app;

    private String address;

    public Order(Map<Product,Integer> productList, Consumer endConsumer, STATUS status, App app,String address){
        this.productsList=productList;
        this.endConsumer=endConsumer;
        this.status=status.PENDING;
        this.rider=null;
        this.app=app;
        this.price=0.0;
        for (Iterator<Map.Entry<Product, Integer>> it = productList.entrySet().iterator(); it.hasNext();) {
            Product p = it.next().getKey();
            System.out.println(p.getPrice()*productList.get(p));
            this.price = this.price + p.getPrice() * productList.get(p);
        }
        this.address=address;
    }

    public Order(Long orderId,Map<Product,Integer> productList, Consumer endConsumer, STATUS status, App app,String address){
        this.productsList=productList;
        this.endConsumer=endConsumer;
        this.status=status.PENDING;
        this.rider=null;
        this.app=app;
        this.price=0.0;
        for (Iterator<Map.Entry<Product, Integer>> it = productList.entrySet().iterator(); it.hasNext();) {
            Product p = it.next().getKey();
            System.out.println(p.getPrice()*productList.get(p));
            this.price = this.price + p.getPrice() * productList.get(p);
        }
        this.address=address;
        this.orderId=orderId;
    }
}
