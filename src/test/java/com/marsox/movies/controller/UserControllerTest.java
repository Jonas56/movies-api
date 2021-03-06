package com.marsox.movies.controller;

import com.marsox.movies.AbstractTest;
import com.marsox.movies.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserControllerTest extends AbstractTest {
    String accessToken;

    @Override
    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        this.setAccessToken(authenticate());
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        // given
        String uri = "/api/v1/users";
        // when
        MvcResult mvcResult = super.mvc.perform(get(uri)
                .header("Authorization", "Bearer " + accessToken)
        ).andReturn();
        // then
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        List<UserDto> userDtoList = super.mapFromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(userDtoList).isNotNull();
    }

    @Test
    void shouldReturnForbiddenErrorWhenUserIsNotAuthenticated() throws Exception {
        // given
        String uri = "/api/v1/users";
        // when
        MvcResult mvcResult = super.mvc.perform(get(uri)
                        .header("Authorization", "Bearer " + accessToken + "ruined")
                )
                .andReturn();
        // then
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(403);
        Map<String, String> responseDto = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        assertThat(responseDto.get("message")).isNotNull();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}