package ua.tqs.cito.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "apps_table")
@Setter
@Getter
@ToString
public class App {
    public App(){};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="appid")
    private Long appid;
    private Double tax;
    private String name;
    private String address;
    private String schedule;

    @Lob
    private String image;



}
