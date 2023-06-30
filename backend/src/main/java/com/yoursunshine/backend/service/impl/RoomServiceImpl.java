package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.entity.Room;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.mapper.RoomMapper;
import com.yoursunshine.backend.repository.RoomRepository;
import com.yoursunshine.backend.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

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

    @Override
    public RoomDetailDto update(Long id, RoomDetailDto room) {
        LOGGER.info("update: {}", room);

        Optional<Room> toUpdate = repository.findById(id);
        if(toUpdate.isPresent()){
            Room existingRoom = toUpdate.get();
            existingRoom.setTitle(room.title());

            return mapper.entityToDetailDto(repository.save(existingRoom));
        } else {
            throw new NotFoundException("Room with id " + id + " does not exist");
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete: {}", id);

        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Room with id " + id + " does not exist");
        }
    }
}
