package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test", "datagen"})
@SpringBootTest
public class RoomServiceTest {
    @Autowired
    private RoomService roomService;

    @Autowired
    private DataGeneratorBean generatorBean;

    @BeforeEach
    public void beforeEach() throws SQLException {
        generatorBean.generateData();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        generatorBean.deleteData();
    }

    @Test
    public void givenNothing_whenCreateWithValidData_thenCreateAndReturnEntry() {
        RoomDetailDto room = roomService.create(new RoomCreateDto("TestRoom"));
        assertNotNull(room);
        assertAll(
                () -> assertNotNull(room.id()),
                () -> assertNotNull(room.title()),
                () -> assertEquals("TestRoom", room.title())
        );
    }

    @Test
    public void givenExistingRooms_whenGetAll_thenReturnAllRooms() {
        List<RoomDetailDto> rooms = roomService.getAll();
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }
}
