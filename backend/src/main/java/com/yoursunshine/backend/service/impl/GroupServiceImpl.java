package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import com.yoursunshine.backend.entity.EspGroup;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.mapper.GroupMapper;
import com.yoursunshine.backend.repository.GroupRepository;
import com.yoursunshine.backend.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final GroupMapper mapper;
    private final GroupRepository repository;

    public GroupServiceImpl(GroupMapper mapper, GroupRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public GroupDetailDto create(GroupCreateDto group) {
        LOGGER.info("create: {}", group);
        EspGroup toCreate = mapper.createDtoToEntity(group);

        return mapper.entityToDetailDto(repository.save(toCreate));
    }

    @Override
    public List<GroupDetailDto> getAll() {
        LOGGER.info("getAll");
        return mapper.entityListToDetailDtoList(repository.findAll());
    }

    @Override
    public GroupDetailDto update(Long id, GroupDetailDto group) {
        LOGGER.info("update: {}", group);

        Optional<EspGroup> toUpdate = repository.findById(id);
        if(toUpdate.isPresent()){
            return mapper.entityToDetailDto(repository.save(mapper.detailDtoToEntity(group)));
        } else {
            throw new NotFoundException("Group with id " + id + " does not exist");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete: {}", id);

        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Group with id " + id + " does not exist");
        }
    }
}
