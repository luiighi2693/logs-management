package com.demo.lab.web.rest;

import com.demo.lab.service.LogService;
import com.demo.lab.service.dto.LogDTO;
import com.demo.lab.service.dto.LogPublicDTO;
import com.demo.lab.service.mapper.LogMapper;
import com.demo.lab.web.rest.errors.BadRequestAlertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing {@link com.demo.lab.domain.Log}.
 */
@RestController
@RequestMapping("/api/log/public/")
public class LogPublicResource {

    private final Logger log = LoggerFactory.getLogger(LogPublicResource.class);

    private static final String ENTITY_NAME = "log";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LogService logService;

    private final LogMapper logMapper;

    public LogPublicResource(LogService logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    /**
     * {@code POST  /logs} : Create a new log.
     *
     * @param logDTO the logDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new logDTO, or with status {@code 400 (Bad Request)} if the log has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/save")
    public ResponseEntity<LogDTO> createLog(@RequestBody LogPublicDTO logDTO) throws URISyntaxException, JsonProcessingException {
        log.debug("REST request to save Log : {}", logDTO);
        if (logDTO.getId() != null) {
            throw new BadRequestAlertException("A new log cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogDTO result = logService.save(logMapper.fromLogPublic(logDTO));
        return ResponseEntity.created(new URI("/api/logs/" + result.getId().replace("\"", "")))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().replace("\"", "")))
            .body(result);
    }
}
