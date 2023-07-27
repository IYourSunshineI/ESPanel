package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleUpdateDto;
import com.yoursunshine.backend.entity.EspGroup;
import com.yoursunshine.backend.entity.RgbModule;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.mapper.RgbModuleMapper;
import com.yoursunshine.backend.repository.GroupRepository;
import com.yoursunshine.backend.repository.RgbModuleRepository;
import com.yoursunshine.backend.service.RgbModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Service
public class RgbModuleServiceImpl implements RgbModuleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final RgbModuleMapper mapper;
    private final RgbModuleRepository repository;
    private final GroupRepository groupRepository;

    public RgbModuleServiceImpl(RgbModuleMapper mapper,
                                RgbModuleRepository repository,
                                GroupRepository groupRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    @Override
    public KnobModuleDetailDto create(Long group_id, RgbModuleCreateDto rgbModule) {
        LOGGER.info("create: group_id: {}, {}", group_id, rgbModule);

        Optional<EspGroup> group = groupRepository.findById(group_id);
        EspGroup foundGroup;
        if(group.isPresent()){
            foundGroup = group.get();
        } else {
            throw new NotFoundException("Group with id " + group_id + " does not exist");
        }

        RgbModule toCreate = mapper.createDtoToEntity(rgbModule);
        toCreate.setGroup(foundGroup);
        return mapper.entityToDetailDto(repository.save(toCreate));
    }

    @Override
    public KnobModuleDetailDto update(Long id, RgbModuleUpdateDto rgbModule) {
        LOGGER.info("update: {}", rgbModule);

        Optional<RgbModule> toUpdate = repository.findById(id);
        if(toUpdate.isPresent()){
            Optional<EspGroup> group = groupRepository.findById(rgbModule.group_id());
            EspGroup foundGroup;
            if(group.isPresent()){
                foundGroup = group.get();
            } else {
                throw new NotFoundException("Group with id " + rgbModule.group_id() + " does not exist");
            }
            RgbModule updated = mapper.updateDtoToEntity(rgbModule);
            updated.setGroup(foundGroup);
            return mapper.entityToDetailDto(repository.save(updated));
        } else {
            throw new NotFoundException("RgbModule with id " + id + " does not exist");
        }
    }
}
