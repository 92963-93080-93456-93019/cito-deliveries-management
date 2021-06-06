package ua.tqs.cito.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long riderId;

    private String fname;
    private String lname;
    private String fnumber;
    private int reputation;
    @ElementCollection
    private List<Integer> reps;

    public Rider(String fname, String lname, String fnumber, int reputation) {
        this.fname=fname;
        this.lname=lname;
        this.fnumber=fnumber;
        this.reputation=reputation;
        this.reps = new ArrayList<>();
    }

    public Rider(Long riderId,String fname, String lname, String fnumber, int reputation) {
        this.fname=fname;
        this.lname=lname;
        this.fnumber=fnumber;
        this.reputation=reputation;
        this.reps = new ArrayList<>();
        this.riderId=riderId;
    }


}
