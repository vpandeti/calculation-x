package com.vpandeti.services.calculationservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpandeti.services.calculationservice.services.LongestPathCalculationService;
import com.vpandeti.services.calculationservice.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LongestPathCalculatonControllerTest {

    @MockBean
    private LongestPathCalculationService longestPathCalculationService;
    @MockBean
    private UserService userService;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Test
    public void testLongestPathCalculation() throws Exception {
        MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();
        List<String> binaryTree = Arrays.asList("1","2","3","4","5","6","7");
        String json = new ObjectMapper().writeValueAsString(binaryTree);
        MockHttpServletResponse response = mockMvc.perform(post("/binaryTree/longestPath")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic andpY2s6andpY2s=")
                .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }
}
