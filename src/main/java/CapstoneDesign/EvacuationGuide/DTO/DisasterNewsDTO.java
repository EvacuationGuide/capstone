package CapstoneDesign.EvacuationGuide.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisasterNewsDTO {

    private Date postDate;
    private String title;
    private String body;

    DisasterNewsDTO disasterNewsDTO = DisasterNewsDTO.builder()
            .postDate(postDate)
            .title(title)
            .body(body)
            .build();
}
