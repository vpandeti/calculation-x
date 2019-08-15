package com.vpandeti.services.calculationservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpandeti.services.calculationservice.entities.User;
import com.vpandeti.services.calculationservice.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserService serviceMock;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Test
    public void testUserLogin() throws Exception {
        MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();
        User user = new User();
        user.setUsername("1234");
        user.setPassword("1234");
        String json = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(post("/user/1234/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(serviceMock).login("1234", "1234");
    }

    @Test
    public void testUserSignUp() throws Exception {
        MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();
        User user = new User();
        user.setUsername("1234");
        user.setPassword("1234");
        user.setFirstName("John");
        user.setLastName("Wick");
        user.setEmail("jwick@wic.com");
        String json = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}
