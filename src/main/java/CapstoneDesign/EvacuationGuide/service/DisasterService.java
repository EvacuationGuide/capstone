package CapstoneDesign.EvacuationGuide.service;

import CapstoneDesign.EvacuationGuide.DTO.DisasterDTO;
import CapstoneDesign.EvacuationGuide.domain.Disaster;
import CapstoneDesign.EvacuationGuide.repository.DisasterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisasterService {

    private final DisasterRepository disasterRepository;

    public void fetchData() {

        // 현재 시간을 LocalDateTime 객체로 가져옵니다.
        LocalDateTime now = LocalDateTime.now();

        // 형식을 지정하기 위한 DateTimeFormatter를 생성합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        // LocalDateTime 객체를 형식에 맞게 포맷팅합니다.
        String formattedDateTime = now.format(formatter);

        // 요청 API
        String apiURL = "https://apihub.kma.go.kr/api/typ01/url/eqk_now.php?tm=" + formattedDateTime + "&disp=0&help=0&authKey=7KxbzC1bQ2-sW8wtW1NvNQ";

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // API 호출 및 응답 수신
        ResponseEntity<String> response = restTemplate.getForEntity(apiURL, String.class);

        // 응답 데이터 받기
        String responseBody = response.getBody();

        System.out.println("responseBody = " + responseBody);


        // 데이터를 줄 단위로 분할하여 배열로 만듭니다.
        String[] lines = responseBody.split("\n");

        // 첫 두 줄은 의미 없으므로 세 번째 줄부터 값을 받아옴
        for (int i = 2; i < lines.length; i++) {
            String line = lines[i];
            // 각 줄을 공백으로 분할하여 필요한 정보를 추출합니다.
            String[] tokens = line.split("\\s+");
            String tp = tokens[1];
            String tmFc = tokens[2];
            String seq = tokens[3];
            String tmEqk = tokens[4];
            String mt = tokens[5];
            String lat = tokens[6];
            String lon = tokens[7];

            StringBuilder descriptionBuilder = new StringBuilder();
            for (int j = 8; j < tokens.length; j++) {
                descriptionBuilder.append(tokens[j]);
                if (j < tokens.length - 1) {
                    descriptionBuilder.append(" ");
                }
            }
            String loc = descriptionBuilder.toString();

            // ,의 위치를 찾음
            int commaIdx = loc.indexOf(',');

            // , 이후 문자열을 없앰
            String trimmedData = (commaIdx != -1) ? loc.substring(0, commaIdx) : loc;

            DisasterDTO dto = DisasterDTO.builder()
                    .type(Integer.parseInt(tp))
                    .occurTime(tmFc)
                    .SEQ(Integer.parseInt(seq))
                    .tmEqk(tmEqk)
                    .magnitude(Float.parseFloat(mt))
                    .latitude(Double.parseDouble(lat))
                    .longitude(Double.parseDouble(lon))
                    .location(trimmedData)
                    .build();

            if (dto.getType() == 3) { // 국내인 경우에만 저장
                Disaster disaster = dto.toEntity();
                disasterRepository.save(disaster);
            }
        }
        List<Disaster> all = disasterRepository.findAll();
        for (Disaster disaster : all) {
            log.info(disaster.toString());
        }
    }


}
