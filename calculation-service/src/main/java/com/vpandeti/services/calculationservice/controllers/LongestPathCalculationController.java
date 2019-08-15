package com.vpandeti.services.calculationservice.controllers;

import com.vpandeti.services.calculationservice.responses.LongestPathCalculationResponse;
import com.vpandeti.services.calculationservice.services.LongestPathCalculationService;
import com.vpandeti.services.calculationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LongestPathCalculationController {

    @Autowired
    LongestPathCalculationService longestPathCalculationService;
    @Autowired
    UserService userService;

    @PostMapping("/binaryTree/longestPath")
    public ResponseEntity<LongestPathCalculationResponse> binaryTreeLongestPath(@RequestBody List<String> binaryTree, @RequestHeader("Authorization") String authorization) {
        userService.findUserByCredentials(authorization);
        List<Integer> longestPath = longestPathCalculationService.binaryTreeLongestPath(binaryTree);
        LongestPathCalculationResponse longestPathCalculationResponse = new LongestPathCalculationResponse();
        longestPathCalculationResponse.setResult(longestPath);
        longestPathCalculationResponse.setStatus("Successfully calculated");
        longestPathCalculationResponse.setError("");
        return new ResponseEntity<>(longestPathCalculationResponse, HttpStatus.OK);
    }
}
