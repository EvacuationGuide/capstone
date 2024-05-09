package CapstoneDesign.EvacuationGuide.DTO;

import CapstoneDesign.EvacuationGuide.domain.Disaster;
import CapstoneDesign.EvacuationGuide.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class DisasterDTO {

    private int type;           // 국내 : 3, 국외 : 2, 값이 3인 경우에만 저장하도록 구현할 것
    private String occurTime;   // 발표시간
    private int SEQ;            // 일련번호, 중복되는 재난 정보는 이 필드로 파악
    private String tmEqk;       // 진앙시
    private float magnitude;    // 규모
    private double latitude;    // 위도
    private double longitude;   // 경도
    private String location;    // 발생 위치

    public Disaster toEntity(){
        return Disaster.builder()
                .SEQ(SEQ)
                .occurTime(occurTime)
                .tmEqk(tmEqk)
                .magnitude(magnitude)
                .latitude(latitude)
                .longitude(longitude)
                .location(location)
                .build();
    }

}
