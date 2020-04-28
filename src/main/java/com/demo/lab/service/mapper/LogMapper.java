package com.demo.lab.service.mapper;


import com.demo.lab.domain.*;
import com.demo.lab.service.dto.LogDTO;

import com.demo.lab.service.dto.LogPublicDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;

import java.util.Map;

/**
 * Mapper for the entity {@link Log} and its DTO {@link LogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LogMapper extends EntityMapper<LogDTO, Log> {

    default Log fromLogPublic(LogPublicDTO logPublicDTO) throws JsonProcessingException {
        Log log = new Log();
        log.setUrl(logPublicDTO.getUrl());
        log.setRequestItem(fromString(logPublicDTO.getRequest()));
        log.setResponseItem(fromString(logPublicDTO.getResponse()));
        log.setStatus(logPublicDTO.getStatus());
        log.setMethod(logPublicDTO.getMethod());
        log.setPlatform(fromString(logPublicDTO.getPlatform()));
        log.setRequestTime(logPublicDTO.getRequestTime());
        log.setResponseTime(logPublicDTO.getResponseTime());
        log.setDuration(logPublicDTO.getDuration());
        log.setLogged(logPublicDTO.isLogged());
        log.setUser(fromString(logPublicDTO.getUser()));
        return log;
    }

    default String fromString(Object request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(request);
    }

    default Log fromId(String id) {
        if (id == null) {
            return null;
        }
        Log log = new Log();
        log.setId(id);
        return log;
    }
}
