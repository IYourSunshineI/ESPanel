package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleUpdateDto;
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
        String json = objectMapper.writeValueAsString(new DimmerModuleCreateDto("TestDimmer", 16));

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
                () -> assertEquals(0, module.brightness())
        );
    }

    @Test
    public void givenNothing_whenCreateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleCreateDto("", 17));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/rooms/-1/groups/-1/dimmermodules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenExistingDimmerModule_whenUpdateWithValidData_thenReturnUpdatedModuleAnd200() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleUpdateDto(-2L, "TestDimmer", 16, 100, -2L));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .put("/rooms/-1/groups/-2/dimmermodules/-2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
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
    public void givenExistingDimmerModule_whenUpdateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleUpdateDto(-2L, "", 17, 300, -2L));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/rooms/-1/groups/-2/dimmermodules/-2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenNonExistingDimmerModule_whenUpdateWithValidData_thenReturn404() throws Exception {
        String json = objectMapper.writeValueAsString(new DimmerModuleUpdateDto(-99L, "TestDimmer", 16, 100, -2L));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/rooms/-1/groups/-2/dimmermodules/-99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
