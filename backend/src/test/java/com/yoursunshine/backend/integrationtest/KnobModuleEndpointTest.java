package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test", "datagen"})
@SpringBootTest
@EnableWebMvc
@WebAppConfiguration
public class KnobModuleEndpointTest {
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
    public void givenExistingModules_whenGetAll_thenReturnAllGroupsAnd200() throws Exception {
        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                .get("/rooms/-1/groups/-1/knobmodules"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        KnobModuleDetailDto[] modules = objectMapper.readValue(body, KnobModuleDetailDto[].class);

        assertNotNull(modules);
        assertEquals(2, modules.length);
    }

    @Test
    public void givenExistingModule_whenDelete_thenDeleteAndReturn204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rooms/-1/groups/-1/knobmodules/-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenNonExistingModule_whenDelete_thenReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rooms/-1/groups/-1/knobmodules/-99"))
                .andExpect(status().isNotFound());
    }
}
