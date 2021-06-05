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
    private String fname;
    private String lname;
    private String phone;
    private String address;


    public Consumer(String fname, String lname,String phone, String address, App app){
        this.fname=fname;
        this.lname=lname;
        this.phone=phone;
        this.address=address;
        this.app=app;
    }

    public Consumer(Long clientId,String fname, String lname,String phone, String address, App app){
        this.fname=fname;
        this.lname=lname;
        this.phone=phone;
        this.address=address;
        this.app=app;
        this.clientId=clientId;
    }
}
