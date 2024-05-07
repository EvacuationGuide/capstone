package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.service.shelter.ShelterService;
import CapstoneDesign.EvacuationGuide.service.shelter.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ShelterController {

    private final ShelterService shelterService;

    @Autowired
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/Shelters")
    public String Shelters() throws IOException {
        shelterService.saveJson();

        return "mail/default";
    }

    @GetMapping("/findShelters")
    public String findShelters() throws IOException {
        shelterService.saveJson();
        shelterService.findShelter();

        return "mail/default";
    }
}
