package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleUpdateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.service.DimmerModuleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles({"test", "datagen"})
@SpringBootTest
public class DimmerModuleServiceTest {
    @Autowired
    private DimmerModuleService dimmerModuleService;
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
        KnobModuleDetailDto module = dimmerModuleService.create(-1L, new DimmerModuleCreateDto("TestModule", 16));
        assertNotNull(module);
        assertAll(
                () -> assertNotNull(module.id()),
                () -> assertNotNull(module.title()),
                () -> assertEquals("TestModule", module.title()),
                () -> assertNotNull(module.pinNumber()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertNotNull(module.brightness()),
                () -> assertEquals(0, module.brightness()),
                () -> assertNotNull(module.group_id()),
                () -> assertEquals(-1L, module.group_id())
        );

    }

    @Test
    public void givenExistingDimmerModule_whenUpdateWithValidData_thenUpdateAndReturnEntry() {
        KnobModuleDetailDto module = dimmerModuleService.update(-2L, new DimmerModuleUpdateDto(-2L, "TestModule", 16, 0, -2L));
        assertNotNull(module);
        assertAll(
                () -> assertNotNull(module.id()),
                () -> assertNotNull(module.title()),
                () -> assertEquals("TestModule", module.title()),
                () -> assertNotNull(module.pinNumber()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertNotNull(module.brightness()),
                () -> assertEquals(0, module.brightness()),
                () -> assertNotNull(module.group_id()),
                () -> assertEquals(-2L, module.group_id())
        );
    }

    @Test
    public void givenNonExistingDimmerModule_whenUpdateWithValidData_thenThrowException() {
        assertThrows(NotFoundException.class, () -> dimmerModuleService.update(-100L, new DimmerModuleUpdateDto(-100L, "TestModule", 16, 0, -2L)));
    }
}
