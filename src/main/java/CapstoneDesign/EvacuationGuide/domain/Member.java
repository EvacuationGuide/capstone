package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Member {

    @Id
    private Long id;

    private String mail;
    private String nickname;
    private String password;

    private boolean pushAlarm;

    private Double latitude;
    private Double longitude;

    public void updateLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}