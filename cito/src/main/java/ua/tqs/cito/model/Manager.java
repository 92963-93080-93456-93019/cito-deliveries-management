package ua.tqs.cito.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long managerId;
    private String fname;
    private String lname;
    private String phone;
    private String address;
    @ManyToOne
    @JoinColumn(name = "appId") // An App has many managers (foreign key)
    private App app;

}
