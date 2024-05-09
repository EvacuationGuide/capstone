package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/disaster")
public class DisasterController {

    private final DisasterService disasterService;

    @GetMapping("/getApi")
    public String getApiPage() {
        return "disaster/getApi";
    }

    @GetMapping("/api")
    public void fetchDisasterData() {
        disasterService.fetchData();
    }


}
