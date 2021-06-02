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
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    @ManyToOne
    @JoinColumn(name = "appId") // An App has many consumers (foreign key)
    private App app;
    private String name;
    private String phone;
    private String address;

}
