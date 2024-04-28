package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String RDNM_Address;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Address(String RDNM_Address, BigDecimal longitude, BigDecimal latitude) {
        this.RDNM_Address = RDNM_Address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
