package CapstoneDesign.EvacuationGuide.service.shelter;

//import aj.org.objectweb.asm.TypeReference;
import CapstoneDesign.EvacuationGuide.domain.Shelter;
import CapstoneDesign.EvacuationGuide.repository.ShelterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;

    @Autowired
    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("json/seoul.json");
        List<Shelter> shelters = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Shelter>>() {});

        for (Shelter shelter : shelters) {
            shelterRepository.save(shelter);
            System.out.println("shelterRepository = " + shelterRepository.getReferenceById(shelter.getId()).getName());
            System.out.println("shelter.getId() = " + shelter.getId());
            System.out.println("shelter.getName() = " + shelter.getName());
            System.out.println("shelter.getArea() = " + shelter.getArea());
            System.out.println("shelter.getX() = " + shelter.getX());
            System.out.println("shelter.getY() = " + shelter.getY());
        }
    }

    public void findShelter(){
        System.out.println(" =================================================================");
        List<Shelter> findShelters=new ArrayList<>();
        List<Shelter> shelters = shelterRepository.findAll();
        for (Shelter shelter : shelters) {
            double distance = distanceInKilometerByHaversine(shelter.getLatitude(), shelter.getLongitude(), 37.5841804304259
                    , 126.924491630657);
            if (distance < 100) {
                System.out.println("distance = " + distance);
                findShelters.add(shelter);
            }
        }

        for (Shelter findShelter : findShelters) {
            System.out.println("findShelter.getName() = " + findShelter.getName());
        }
    }


    public static double distanceInKilometerByHaversine(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distance;
        double radius = 6371; // 지구 반지름(km)
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(latitude1 - latitude2) * toRadian;
        double deltaLongitude = Math.abs(longitude1 - longitude2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(latitude1 * toRadian) * Math.cos(latitude2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }
}
