package com.marsox.movies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marsox.movies.AbstractTest;
import com.marsox.movies.dto.UserDto;
import com.marsox.movies.dto.UserRegistrationDto;
import com.marsox.movies.model.User;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    @Description("Should return 201 CREATED status response and the user object when registering a valid user")
    void shouldAddNewUser() throws Exception {
        // given
        String uri = "/api/v1/signup";
        UserRegistrationDto validUser = UserRegistrationDto.build(
                "Jonas Tesla",
                "Issac56",
                "issac@tesla.com",
                "Jonas.@123"
        );

        String inputJson = super.mapToJson(validUser);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
        ).andReturn();

        // then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        UserDto userCreated = mapFromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        assertThat(userCreated).isNotNull();
    }

    @Test
    @Description("Should return 400 status response with error message when registering duplicate user")
    void shouldNotAddDuplicateUser() throws Exception {
        // given
        String uri = "/api/v1/signup";
        UserRegistrationDto duplicateUser = UserRegistrationDto.build(
                "Jonas Tesla",
                "Jonas56",
                "issac@tesla.com",
                "Jonas.@123"
        );

        String inputJson = super.mapToJson(duplicateUser);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
        ).andReturn();

        // then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        Map<String, String> objectResponse = mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        assertThat(objectResponse.get("message")).isEqualTo("User already exists");
    }

    @Test
    @Description("Should return 400 status response with error message when registering invalid user")
    void shouldNotAddInvalidUser() throws Exception {
        // given
        String uri = "/api/v1/signup";
        UserRegistrationDto invalidUser = UserRegistrationDto.build(
                null,
                "Jonas56",
                "issac@",
                "Jonas.@123"
        );

        String inputJson = super.mapToJson(invalidUser);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
        ).andReturn();

        // then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        Map<String, String> userResponse = mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        assertThat(userResponse.get("email")).isEqualTo("Please provide valid email");
        assertThat(userResponse.get("fullName")).isEqualTo("full name cannot be empty!");
    }
}