package com.datta.cardiac_arrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_predictions")
public class UserPrediction {
    @Id
    private String id;


    private PredictionResponse predictionResponse;


    private UserHealthData userHealthData;
}
