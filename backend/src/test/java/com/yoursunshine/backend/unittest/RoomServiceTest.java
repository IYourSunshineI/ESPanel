package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.exception.NotFoundException;
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

    @Test
    public void givenExistingRoom_whenUpdateWithValidData_thenUpdateAndReturnEntry() {
        RoomDetailDto room = roomService.update(-1L, new RoomDetailDto(-1L, "TestRoom"));
        assertNotNull(room);
        assertAll(
                () -> assertNotNull(room.id()),
                () -> assertNotNull(room.title()),
                () -> assertEquals("TestRoom", room.title())
        );
    }

    @Test
    public void givenNonExistingRoom_whenUpdateWithValidData_thenThrowException() {
        assertThrows(NotFoundException.class, () -> roomService.update(-99L, new RoomDetailDto(-99L, "TestRoom")));
    }

    @Test
    public void givenExistingRoom_whenDelete_thenDelete() {
        int sizeBefore = roomService.getAll().size();
        roomService.delete(-1L);
        List<RoomDetailDto> rooms = roomService.getAll();
        assertNotNull(rooms);
        assertEquals(sizeBefore - 1, rooms.size());
    }

    @Test
    public void givenNonExistingRoom_whenDelete_thenThrowException() {
        assertThrows(NotFoundException.class, () -> roomService.delete(-99L));
    }
}
