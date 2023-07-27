package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.mapper.KnobModuleMapper;
import com.yoursunshine.backend.repository.KnobModuleRepository;
import com.yoursunshine.backend.service.KnobModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class KnobModuleServiceImpl implements KnobModuleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final KnobModuleMapper mapper;
    private final KnobModuleRepository repository;

    public KnobModuleServiceImpl(KnobModuleMapper mapper, KnobModuleRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public List<KnobModuleDetailDto> getAll() {
        LOGGER.info("getAll");
        return mapper.entityListToDetailDtoList(repository.findAll());
    }
}