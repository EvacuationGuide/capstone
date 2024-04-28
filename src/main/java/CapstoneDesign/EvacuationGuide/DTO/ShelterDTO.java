package CapstoneDesign.EvacuationGuide.DTO;

import CapstoneDesign.EvacuationGuide.domain.Address;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShelterDTO {

    private String name;
    private int capacity;
    private Address address;

    private int test;
    private int test2;
    
    ShelterDTO shelterDTO = ShelterDTO.builder()
            .name(name)
            .capacity(capacity)
            .address(address)
            .build();
}
