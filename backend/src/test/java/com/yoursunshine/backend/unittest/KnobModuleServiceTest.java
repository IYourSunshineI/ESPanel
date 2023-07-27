package com.yoursunshine.backend.unittest;

import com.yoursunshine.backend.datagenerator.DataGeneratorBean;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.service.KnobModuleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles({"test", "datagen"})
@SpringBootTest
public class KnobModuleServiceTest {
    @Autowired
    private KnobModuleService knobModuleService;
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
    public void givenExistingModules_whenGetAll_thenReturnAllModules() {
        assertEquals(2, knobModuleService.getAll(-1L).size());
    }

    @Test
    public void givenExistingModule_whenDelete_thenDelete() {
        int sizeBefore = knobModuleService.getAll(-1L).size();
        knobModuleService.delete(-1L);
        int sizeAfter = knobModuleService.getAll(-1L).size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void givenNonExistingModule_whenDelete_thenThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> knobModuleService.delete(-100L));
    }
}
