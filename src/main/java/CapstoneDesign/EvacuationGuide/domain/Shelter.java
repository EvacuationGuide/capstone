package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_id")
    private Long id;

    private String name;
    private int Capacity;

    @Embedded
    private Address address;

    public Shelter(String name, int capacity, Address address) {
        this.name = name;
        Capacity = capacity;
        this.address = address;
    }
}
