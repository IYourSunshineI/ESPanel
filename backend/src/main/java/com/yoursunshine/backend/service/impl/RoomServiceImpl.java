package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.entity.Room;
import com.yoursunshine.backend.mapper.RoomMapper;
import com.yoursunshine.backend.repository.RoomRepository;
import com.yoursunshine.backend.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final RoomMapper mapper;
    private final RoomRepository repository;

    public RoomServiceImpl(RoomMapper mapper, RoomRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public RoomDetailDto create(RoomCreateDto room) {
        LOGGER.info("create: {}", room);
        Room toCreate = mapper.createDtoToEntity(room);

        return mapper.entityToDetailDto(repository.save(toCreate));
    }

    @Override
    public List<RoomDetailDto> getAll() {
        LOGGER.info("getAll");
        return mapper.entityListToDetailDtoList(repository.findAll());
    }
}
