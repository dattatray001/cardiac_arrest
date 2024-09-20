package com.datta.cardiac_arrest.service;

import com.datta.cardiac_arrest.model.PredictionResponse;
import com.datta.cardiac_arrest.model.UserHealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HealthService {

    @Autowired
    private  RestTemplate restTemplate;

    @PostMapping("/send-health-data")
    public ResponseEntity<PredictionResponse> cardiacPredict(@RequestBody List<UserHealthData> healthData) {
        // URL of the external API you want to send the data to
        String externalApiUrl = "http://127.0.0.1:5000/predict";

        // Sending data to the external API and receiving the response
        ResponseEntity<PredictionResponse> response = restTemplate.postForEntity(externalApiUrl, healthData, PredictionResponse.class);

        // Return the response (prediction) from the external API
        return ResponseEntity.ok(response.getBody());
    }


}
