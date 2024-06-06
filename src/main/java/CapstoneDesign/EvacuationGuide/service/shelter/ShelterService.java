package CapstoneDesign.EvacuationGuide.service.shelter;

//import aj.org.objectweb.asm.TypeReference; 해당 import와
//import com.fasterxml.jackson.core.type.TypeReference;가 충돌

import CapstoneDesign.EvacuationGuide.domain.Shelter;
import CapstoneDesign.EvacuationGuide.repository.ShelterRepository;
import CapstoneDesign.EvacuationGuide.usefulMethod.Haversine;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Shelter> shelters = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Shelter>>() {
        });

        for (Shelter shelter : shelters) {
            shelterRepository.save(shelter);
        }
    }

    public void AllShelters() {
        List<Shelter> shelters = shelterRepository.findAll();
        for (Shelter shelter : shelters) {
            System.out.println("");
            System.out.println("대피소 이름 = " + shelterRepository.getReferenceById(shelter.getId()).getName());
            System.out.println("대피소 도로명 주소 = " + shelterRepository.getReferenceById(shelter.getId()).getAddress_street());
            System.out.println("대피소 최대수용인원 = " + shelterRepository.getReferenceById(shelter.getId()).getOccupancy() + "명");
        }
    }

    public void findShelters() {
        Shelter shortestShelter = null;
        Double shortestDistance = 5.0;
        Map<Shelter, Double> findshelterMap = new HashMap<Shelter, Double>();
        Haversine haversine = new Haversine();

        List<Shelter> findShelters = new ArrayList<>();
        List<Shelter> shelters = shelterRepository.findAll();
        for (Shelter shelter : shelters) {
            double distance = haversine.distanceInKilometerByHaversine(shelter.getLatitude(),
                    shelter.getLongitude(),
                    37.579617
                    , 126.977041);
            if (distance < 5) {   //5km
                findShelters.add(shelter);
                findshelterMap.put(shelter, distance);
                if (distance < shortestDistance) {
                    shortestShelter = shelter;
                    shortestDistance = distance;
                }
            }
        }

        System.out.println("=================================================================");
        try {
            System.out.println("현재 위치로부터 가장 가까운 대피소는" + shortestShelter.getName() + "입니다");
        } catch (NullPointerException e) {
            System.out.println("현재 위치로부터 5km 안에 존재하는 대피소가 없습니다");
            return;
        }
        System.out.printf("해당 대피소까지의 직선거리는 약 ");
        System.out.printf("%.2f", shortestDistance); //소수점 2자리까지만 출력
        System.out.println("km 입니다.");
        System.out.println("=================================================================");
        System.out.println("현재 위치로부터 직선거리 5km 내 위치한 대피소는 총 " + findShelters.size() + "개입니다.");
        System.out.println("리스트는 아래와 같습니다.");
        for (Shelter findShelter : findShelters) {
            System.out.printf("[" + findShelter.getName());
            System.out.println(", " + String.format("%.2f", findshelterMap.get(findShelter)) + "km]");
        }
    }
}

//    //두 지점 사이의 직선 거리 구하는 공식
//    //두 지점 각각의 위도, 경도 필요
//    public static double distanceInKilometerByHaversine(double latitude1, double longitude1, double latitude2, double longitude2) {
//        double distance;
//        double radius = 6371; // 지구 반지름(km)
//        double toRadian = Math.PI / 180;
//
//        double deltaLatitude = Math.abs(latitude1 - latitude2) * toRadian;
//        double deltaLongitude = Math.abs(longitude1 - longitude2) * toRadian;
//
//        double sinDeltaLat = Math.sin(deltaLatitude / 2);
//        double sinDeltaLng = Math.sin(deltaLongitude / 2);
//        double squareRoot = Math.sqrt(
//                sinDeltaLat * sinDeltaLat +
//                        Math.cos(latitude1 * toRadian) * Math.cos(latitude2 * toRadian) * sinDeltaLng * sinDeltaLng);
//
//        distance = 2 * radius * Math.asin(squareRoot);
//
//        return distance;
//    }

