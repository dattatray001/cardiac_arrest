package com.datta.cardiac_arrest.service;

import com.datta.cardiac_arrest.model.PredictionResponse;
import com.datta.cardiac_arrest.model.UserHealthData;
import com.datta.cardiac_arrest.model.UserPrediction;
import com.datta.cardiac_arrest.repo.UserPredictionRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HealthService {

    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private UserService service;

    @Autowired
    private UserPredictionRepo userPredictionRepo;


    public ResponseEntity<PredictionResponse> cardiacPredict(@RequestBody UserHealthData healthData, HttpServletRequest request) {
        // URL of the external API you want to send the data to
        log.error(healthData.toString());
        String externalApiUrl = "http://127.0.0.1:5000/predict";

        List<UserHealthData> healthData1=new ArrayList<>();
        healthData1.add(healthData);
        log.info(healthData1.toString());

        // Sending data to the external API and receiving the response
        ResponseEntity<PredictionResponse> response = restTemplate.postForEntity(externalApiUrl, healthData1, PredictionResponse.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserPrediction userPrediction=new UserPrediction();
        userPrediction.setPredictionResponse(response.getBody());

        userPrediction.setUserHealthData(healthData);
        UserPrediction userPrediction1=userPredictionRepo.save(userPrediction);
        log.error(userPrediction1.toString());
        service.updateReport(userName,userPrediction1);



        // Return the response (prediction) from the external API
        return ResponseEntity.ok(response.getBody());
    }


}
