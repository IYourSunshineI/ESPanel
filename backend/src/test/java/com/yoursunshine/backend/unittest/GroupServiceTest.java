package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.service.GroupService;
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
public class GroupServiceTest {
    @Autowired
    private GroupService groupService;

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
        GroupDetailDto group = groupService.create(new GroupCreateDto("TestGroup", "111.111.111.111"));
        assertNotNull(group);
        assertAll(
                () -> assertNotNull(group.id()),
                () -> assertNotNull(group.title()),
                () -> assertEquals("TestGroup", group.title()),
                () -> assertNotNull(group.ip_address()),
                () -> assertEquals("111.111.111.111", group.ip_address()),
                () -> assertFalse(group.state())
        );
    }

    @Test
    public void givenExistingGroups_whenGetAll_thenReturnAllGroups() {
        List<GroupDetailDto> groups = groupService.getAll();
        assertNotNull(groups);
        assertEquals(2, groups.size());
    }

    @Test
    public void givenExistingGroup_whenUpdateWithValidData_thenUpdateAndReturnEntry() {
        GroupDetailDto group = groupService.update(-1L, new GroupDetailDto(-1L, "TestGroup", "222.222.222.222", true));
        assertNotNull(group);
        assertAll(
                () -> assertNotNull(group.id()),
                () -> assertNotNull(group.title()),
                () -> assertEquals("TestGroup", group.title()),
                () -> assertNotNull(group.ip_address()),
                () -> assertEquals("222.222.222.222", group.ip_address()),
                () -> assertTrue(group.state())
        );
    }

    @Test
    public void givenNonExistingGroup_whenUpdateWithValidData_thenThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> groupService.update(-99L, new GroupDetailDto(-99L, "TestGroup", "111.111.111.111", true)));
    }

    @Test
    public void givenExistingGroup_whenDelete_thenDelete() {
        int sizeBefore = groupService.getAll().size();
        groupService.delete(-1L);
        int sizeAfter = groupService.getAll().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void givenNonExistingGroup_whenDelete_thenThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> groupService.delete(-99L));
    }
}
