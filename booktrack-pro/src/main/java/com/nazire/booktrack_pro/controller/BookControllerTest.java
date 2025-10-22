package com.nazire.booktrack_pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazire.booktrack_pro.dto.CreateBookRequest;
import com.nazire.booktrack_pro.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockitoBean BookService service;

    @Test
    void create_validation_error() throws Exception{
        CreateBookRequest request = new CreateBookRequest();
        mvc.perform(post("/api/books")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content(om.writeValueAsString(request)))
             .andExpect(status().isBadRequest())
             .andExpect((ResultMatcher) jsonPath("$.validationErrors.title").exists());
    }

}
