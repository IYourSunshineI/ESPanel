package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleUpdateDto;
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
public class RgbModuleEndpointTest {
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
        String json = objectMapper.writeValueAsString(new RgbModuleCreateDto("TestRGB", 16));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/-1/rgbmodules")
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
                () -> assertEquals("TestRGB", module.title()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertEquals("#000000", module.color()),
                () -> assertEquals(-1L, module.group_id())
        );
    }

    @Test
    public void givenNothing_whenCreateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new RgbModuleCreateDto("", 17));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/-1/rgbmodules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenExistingRgbModule_whenUpdateWithValidData_thenReturnUpdatedModuleAnd200() throws Exception {
        String json = objectMapper.writeValueAsString(new RgbModuleUpdateDto(-3L, "TestRGB", 16, "#FF00FF", -1L));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .put("/groups/-1/rgbmodules/-3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        KnobModuleDetailDto module = objectMapper.readValue(body, KnobModuleDetailDto.class);

        assertAll(
                () -> assertNotNull(module),
                () -> assertEquals(-3L, module.id()),
                () -> assertEquals("TestRGB", module.title()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertEquals("#FF00FF", module.color()),
                () -> assertEquals(-1L, module.group_id())
        );
    }

    @Test
    public void givenExistingRgbModule_whenUpdateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new RgbModuleUpdateDto(-3L, "", 17, "#FF00FF", -1L));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/groups/-1/rgbmodules/-3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenNonExistingRgbModule_whenUpdateWithValidData_thenReturn404() throws Exception {
        String json = objectMapper.writeValueAsString(new RgbModuleUpdateDto(-99L, "TestRGB", 16, "#FF00FF", -1L));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/groups/-1/rgbmodules/-99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
