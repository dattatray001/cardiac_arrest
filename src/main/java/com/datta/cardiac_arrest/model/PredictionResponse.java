package com.datta.cardiac_arrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResponse {
    private int predicted_class;
    private double predicted_probability;
}
