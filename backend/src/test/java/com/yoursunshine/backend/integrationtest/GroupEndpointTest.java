package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
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
public class GroupEndpointTest {
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
    public void givenNothing_whenCreateWithValidData_thenCreateAndReturnEntryAnd201() throws Exception{
        String json = objectMapper.writeValueAsString(new GroupCreateDto("TestGroup", "1.1.1.1"));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                .post("/rooms/-1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        GroupDetailDto group = objectMapper.readValue(body, GroupDetailDto.class);

        assertAll(
                () -> assertNotNull(group),
                () -> assertNotNull(group.id()),
                () -> assertEquals("TestGroup", group.title()),
                () -> assertNotNull(group.ip_address()),
                () -> assertEquals("1.1.1.1", group.ip_address()),
                () -> assertFalse(group.state()),
                () -> assertNotNull(group.room_id()),
                () -> assertEquals(-1L, group.room_id())
        );
    }

    @Test
    public void givenNothing_whenCreateWithInvalidData_thenReturn422() throws Exception {
        String json = objectMapper.writeValueAsString(new GroupCreateDto("title", "abcs"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/rooms/-1/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenExistingGroups_whenGetAll_thenReturnAllGroupsAnd200() throws Exception {
        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rooms/-1/groups"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        GroupDetailDto[] groups = objectMapper.readValue(body, GroupDetailDto[].class);

        assertNotNull(groups);
        assertEquals(2, groups.length);
    }

    @Test
    public void givenExistingGroup_whenUpdateWithValidData_thenReturnUpdatedGroupAnd200() throws Exception {
        String json = objectMapper.writeValueAsString(new GroupDetailDto(-1L, "TestGroup", "2.2.2.2", true, -1L));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                        .put("/rooms/-1/groups/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        GroupDetailDto group = objectMapper.readValue(body, GroupDetailDto.class);

        assertAll(
                () -> assertNotNull(group),
                () -> assertEquals(-1L, group.id()),
                () -> assertEquals("TestGroup", group.title()),
                () -> assertEquals("2.2.2.2", group.ip_address()),
                () -> assertTrue(group.state()),
                () -> assertNotNull(group.room_id()),
                () -> assertEquals(-1L, group.room_id())
        );
    }

    @Test
    public void givenNonExistingGroup_whenUpdateWithValidData_thenReturn404() throws Exception {
        String json = objectMapper.writeValueAsString(new GroupDetailDto(-99L, "TestGroup", "1.1.1.1", true, -1L));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/rooms/-1/groups/-99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenExistingGroup_whenDelete_thenDeleteAnd204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/rooms/-1/groups/-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenNonExistingGroup_whenDelete_thenReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/rooms/-1/groups/-99"))
                .andExpect(status().isNotFound());
    }
}
