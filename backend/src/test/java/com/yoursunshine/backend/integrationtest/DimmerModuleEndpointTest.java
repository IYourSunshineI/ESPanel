package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test", "datagen"})
@SpringBootTest
@EnableWebMvc
@WebAppConfiguration
public class DimmerModuleEndpointTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataGeneratorBean generatorBean;

    @BeforeEach
    public void beforeEach() throws SQLException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        generatorBean.generateData();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        generatorBean.deleteData();
    }

    @Test
    public void givenNothing_whenCreateWithValidData_thenCreateAndReturnEntryAnd201() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleCreateDto("TestDimmer", 16, 100));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .post("/rooms/-1/groups/-1/dimmermodules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        KnobModuleDetailDto module = objectMapper.readValue(body, KnobModuleDetailDto.class);

        assertAll(
                () -> assertNotNull(module),
                () -> assertNotNull(module.id()),
                () -> assertEquals("TestDimmer", module.title()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertEquals(100, module.brightness())
        );
    }

    @Test
    public void givenNothing_whenCreateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleCreateDto("", 17, 300));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/rooms/-1/groups/-1/dimmermodules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }
}
