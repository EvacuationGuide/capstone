package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disaster_id")
    private Long id;

    private String occurTime;   // 발표시간
    private int SEQ;            // 일련번호, 중복되는 재난 정보는 이 필드로 파악
    private String tmEqk;       // 진앙시
    private float magnitude;    // 규모
    private double latitude;    // 위도
    private double longitude;   // 경도
    private String location;    // 발생 위치

}
