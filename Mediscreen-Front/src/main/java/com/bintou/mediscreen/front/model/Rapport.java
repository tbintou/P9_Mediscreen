package com.bintou.mediscreen.front.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Rapport {

    private Patient patient;

    private int patientAge;

    private String riskDiabeteLevel;

}
