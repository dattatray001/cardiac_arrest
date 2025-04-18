package com.datta.cardiac_arrest.controller;

import com.datta.cardiac_arrest.model.PredictionResponse;
import com.datta.cardiac_arrest.model.UserHealthData;
import com.datta.cardiac_arrest.service.HealthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthDataController {

    @Autowired
    private HealthService healthService;


    @PostMapping("/send-health-data")
    public ResponseEntity<PredictionResponse> sendHealthData(@RequestBody UserHealthData healthData, HttpServletRequest request) {

        ResponseEntity<PredictionResponse> response=healthService.cardiacPredict(healthData, request);

        return ResponseEntity.ok(response.getBody());
    }
}
