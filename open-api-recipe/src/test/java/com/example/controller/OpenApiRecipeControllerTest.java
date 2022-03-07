package com.example.controller;

import com.example.service.OpenApiServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OpenApiRecipeController.class)
@AutoConfigureMockMvc
public class OpenApiRecipeControllerTest {

    @MockBean
    private OpenApiServiceImpl openApiServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Open-API 리스트 반환 성공")
    @Test
    public void getAllRecipes() throws Exception {
        // given
        URI uri = UriComponentsBuilder.newInstance()
                .path("/open-apis/all")
                .build()
                .toUri();

        given(this.openApiServiceImpl.getListAll())
                .willReturn(new ArrayList<>());

        // when
        ResultActions actions = mockMvc.perform(get(uri));

        //then
        actions.andExpect(status().isOk())
                .andDo(print());

    }
}
