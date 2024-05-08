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

    private String name;                //주소
    private String address_street;      //도로명 주소
    private String address_parcel;      //소재지 주소
    private int postcode;               //우편번호
    private Long area;                  //시설 면적
    private int occupancy;              //최대 수용 인원
    private Double latitude_degrees;    //위도(도)
    private Double latitude_minutes;    //위도(분)
    private Double latitude_seconds;    //위도(초)
    private Double longitude_degrees;   //경도(도)
    private Double longitude_minutes;   //경도(분)
    private Double longitude_seconds;   //경도(초)
    private Double latitude;            //전체 위도
    private Double longitude;           //전체 경도
    private Double x;                   //X좌표
    private Double y;                   //Y좌표
}
