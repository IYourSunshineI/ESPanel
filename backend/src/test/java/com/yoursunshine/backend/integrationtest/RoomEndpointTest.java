package com.yoursunshine.backend.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test", "datagen"})
@SpringBootTest
@EnableWebMvc
@WebAppConfiguration
public class RoomEndpointTest {
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
        String json = objectMapper.writeValueAsString(new RoomCreateDto("TestRoom"));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                .post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        RoomDetailDto room = objectMapper.readValue(body, RoomDetailDto.class);

        assertNotNull(room);
        assertAll(
                () -> assertNotNull(room.id()),
                () -> assertNotNull(room.title()),
                () -> assertEquals("TestRoom", room.title())
        );
    }

    @Test
    public void givenNothing_whenCreateWithInvalidData_thenReturn422() throws Exception{
        String json = objectMapper.writeValueAsString(new RoomCreateDto(""));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void givenExistingRooms_whenGetAll_thenReturnAll() throws Exception {
        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                .get("/rooms"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        RoomDetailDto[] rooms = objectMapper.readValue(body, RoomDetailDto[].class);

        assertNotNull(rooms);
        assertEquals(2, rooms.length);
    }

    @Test
    public void givenExistingRoom_whenUpdateWithValidData_thenReturnUpdatedRoom() throws Exception {
        String json = objectMapper.writeValueAsString(new RoomDetailDto(-1L, "UpdatedRoom"));

        byte[] body = mockMvc.perform(MockMvcRequestBuilders
                .put("/rooms/-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        RoomDetailDto room = objectMapper.readValue(body, RoomDetailDto.class);

        assertNotNull(room);
        assertAll(
                () -> assertNotNull(room.id()),
                () -> assertNotNull(room.title()),
                () -> assertEquals("UpdatedRoom", room.title())
        );
    }

    @Test
    public void givenNonExistingRoom_whenUpdateWithValidData_thenReturn404() throws Exception {
        String json = objectMapper.writeValueAsString(new RoomDetailDto(-99L, "UpdatedRoom"));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/rooms/-99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenExistingRoom_whenDelete_thenDeleteAndReturn204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rooms/-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenNonExistingRoom_whenDelete_thenReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rooms/-99"))
                .andExpect(status().isNotFound());
    }

}
