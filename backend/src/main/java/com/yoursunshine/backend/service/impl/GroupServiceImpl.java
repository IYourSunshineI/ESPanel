package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import com.yoursunshine.backend.entity.EspGroup;
import com.yoursunshine.backend.entity.Room;
import com.yoursunshine.backend.exception.NotFoundException;
import com.yoursunshine.backend.mapper.GroupMapper;
import com.yoursunshine.backend.repository.GroupRepository;
import com.yoursunshine.backend.repository.RoomRepository;
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
    private final RoomRepository roomRepository;

    public GroupServiceImpl(GroupMapper mapper, GroupRepository repository, RoomRepository roomRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public GroupDetailDto create(Long room_id, GroupCreateDto group) {
        LOGGER.info("create: room_id: {}, {}", room_id, group);

        Optional<Room> room = roomRepository.findById(room_id);
        Room foundRoom;
        if(room.isPresent()){
            foundRoom = room.get();
        } else {
            throw new NotFoundException("Room with id " + room_id + " does not exist");
        }

        EspGroup toCreate = mapper.createDtoToEntity(group);
        toCreate.setRoom(foundRoom);
        return mapper.entityToDetailDto(repository.save(toCreate));
    }

    @Override
    public List<GroupDetailDto> getAll(Long room_id) {
        LOGGER.info("getAll");
        return mapper.entityListToDetailDtoList(repository.getAll(room_id));
    }

    @Override
    public GroupDetailDto update(Long id, GroupDetailDto group) {
        LOGGER.info("update: {}", group);

        Optional<EspGroup> toUpdate = repository.findById(id);
        if(toUpdate.isPresent()){
            Optional<Room> room = roomRepository.findById(group.room_id());
            Room foundRoom;
            if(room.isPresent()){
                foundRoom = room.get();
            } else {
                throw new NotFoundException("Room with id " + group.room_id() + " does not exist");
            }
            EspGroup updated = mapper.detailDtoToEntity(group);
            updated.setRoom(foundRoom);
            return mapper.entityToDetailDto(repository.save(updated));
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
