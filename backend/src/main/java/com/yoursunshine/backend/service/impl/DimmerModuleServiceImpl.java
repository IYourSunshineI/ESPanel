package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleUpdateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.entity.DimmerModule;
import com.yoursunshine.backend.entity.EspGroup;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.mapper.DimmerModuleMapper;
import com.yoursunshine.backend.repository.DimmerModuleRepository;
import com.yoursunshine.backend.repository.GroupRepository;
import com.yoursunshine.backend.service.DimmerModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Service
public class DimmerModuleServiceImpl implements DimmerModuleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final DimmerModuleMapper mapper;
    private final DimmerModuleRepository repository;
    private final GroupRepository groupRepository;

    public DimmerModuleServiceImpl(DimmerModuleMapper mapper,
                                   DimmerModuleRepository repository,
                                   GroupRepository groupRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    @Override
    public KnobModuleDetailDto create(Long group_id, DimmerModuleCreateDto dimmerModule) {
        LOGGER.info("create: group_id: {}, {}", group_id, dimmerModule);

        Optional<EspGroup> group = groupRepository.findById(group_id);
        EspGroup foundGroup;
        if(group.isPresent()){
            foundGroup = group.get();
        } else {
            throw new NotFoundException("Group with id " + group_id + " does not exist");
        }

        DimmerModule toCreate = mapper.createDtoToEntity(dimmerModule);
        toCreate.setGroup(foundGroup);
        return mapper.entityToDetailDto(repository.save(toCreate));
    }

    @Override
    public KnobModuleDetailDto update(Long id, DimmerModuleUpdateDto dimmerModule) {
        LOGGER.info("update: {}", dimmerModule);

        Optional<DimmerModule> toUpdate = repository.findById(id);
        if(toUpdate.isPresent()) {
            Optional<EspGroup> group = groupRepository.findById(dimmerModule.group_id());
            EspGroup foundGroup;
            if (group.isPresent()) {
                foundGroup = group.get();
            } else {
                throw new NotFoundException("Group with id " + dimmerModule.group_id() + " does not exist");
            }
            DimmerModule updated = mapper.updateDtoToEntity(dimmerModule);
            updated.setGroup(foundGroup);
            return mapper.entityToDetailDto(repository.save(updated));
        } else {
            throw new NotFoundException("DimmerModule with id " + id + " does not exist");
        }
    }
}
