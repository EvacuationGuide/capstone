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
    private String address_street;
    private String address_parcel;
    private int postcode;
    private Long area;
    private int occupancy;
    private Long latitude_degrees;
    private Long latitude_minutes;
    private Long latitude_seconds;
    private Long longitude_degrees;
    private Long longitude_minutes;
    private Long longitude_seconds;
    private Long latitude;
    private Long longitude;
    private Long x;
    private Long y;




}
