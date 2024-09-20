package com.datta.cardiac_arrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHealthData {
    private int age;
    private int gender;
    private int height;
    private int weight;
    private int ap_hi;
    private int ap_lo;
    private int cholesterol;
    private int gluc;
    private int smoke;
    private int alco;
}
