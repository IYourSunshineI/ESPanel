package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleUpdateDto;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.service.RgbModuleService;
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
public class RgbModuleServiceTest {
    @Autowired
    private RgbModuleService rgbModuleService;
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
        KnobModuleDetailDto module = rgbModuleService.create(-1L, new RgbModuleCreateDto("TestModule", 16));
        assertNotNull(module);
        assertAll(
                () -> assertNotNull(module.id()),
                () -> assertNotNull(module.title()),
                () -> assertEquals("TestModule", module.title()),
                () -> assertNotNull(module.pinNumber()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertNotNull(module.color()),
                () -> assertEquals("#000000", module.color()),
                () -> assertNotNull(module.group_id()),
                () -> assertEquals(-1L, module.group_id())
        );
    }

    @Test
    public void givenExistingRgbModule_whenUpdateWithValidData_thenUpdateAndReturnEntry() {
        KnobModuleDetailDto module = rgbModuleService.update(-3L, new RgbModuleUpdateDto(-3L, "TestModule", 16, "#FF00FF", -1L));
        assertNotNull(module);
        assertAll(
                () -> assertNotNull(module.id()),
                () -> assertNotNull(module.title()),
                () -> assertEquals("TestModule", module.title()),
                () -> assertNotNull(module.pinNumber()),
                () -> assertEquals(16, module.pinNumber()),
                () -> assertNotNull(module.color()),
                () -> assertEquals("#FF00FF", module.color()),
                () -> assertNotNull(module.group_id()),
                () -> assertEquals(-1L, module.group_id())
        );
    }

    @Test
    public void givenNonExistingRgbModule_whenUpdateWithValidData_thenThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> rgbModuleService.update(-100L, new RgbModuleUpdateDto(-100L, "TestModule", 16, "#FF00FF", -1L)));
    }
}
