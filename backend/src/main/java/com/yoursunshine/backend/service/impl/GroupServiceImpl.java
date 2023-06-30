package com.yoursunshine.backend.service.impl;

import com.yoursunshine.backend.mapper.GroupMapper;
import com.yoursunshine.backend.repository.GroupRepository;
import com.yoursunshine.backend.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final GroupMapper mapper;
    private final GroupRepository repository;

    public GroupServiceImpl(GroupMapper mapper, GroupRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
}
