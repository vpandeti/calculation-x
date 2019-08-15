package com.vpandeti.services.calculationservice.services;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LongestPathCalculationServiceTest {

    @Test
    public void testLongestPathCalculationTest() {
        LongestPathCalculationService longestPathCalculationService = new LongestPathCalculationService();
        List<Integer> expected = longestPathCalculationService.binaryTreeLongestPath(Arrays.asList("1","2","3","4","5","6","7"));
        Assert.assertEquals(expected.toString(), "[1, 3, 7]");
    }
}
