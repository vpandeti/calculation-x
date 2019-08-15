package com.vpandeti.services.calculationservice.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors
public class LongestPathCalculationResponse {
    private List<Integer> result;
    private String status;
    private String error;
}
