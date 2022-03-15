package com.example.service;

import com.example.repository.OpenApiRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
public class OpenApiServiceImplTest {

    @MockBean
    private OpenApiRepository openApiRepository;

    @DisplayName("Open-API data 저장 성공")
    @Test
    public void getAllRecipes() throws Exception {
        // given
        given(new ArrayList<>());

        // when
        when(this.openApiRepository.findAll().size()).thenReturn(1314);

        //then
    }
}
