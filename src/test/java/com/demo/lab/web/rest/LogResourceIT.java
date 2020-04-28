package com.demo.lab.web.rest;

import com.demo.lab.LogSystemApp;
import com.demo.lab.domain.Log;
import com.demo.lab.repository.LogRepository;
import com.demo.lab.service.LogService;
import com.demo.lab.service.dto.LogDTO;
import com.demo.lab.service.mapper.LogMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LogResource} REST controller.
 */
@SpringBootTest(classes = LogSystemApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LogResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_PLATFORM = "AAAAAAAAAA";
    private static final String UPDATED_PLATFORM = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final Boolean DEFAULT_LOGGED = false;
    private static final Boolean UPDATED_LOGGED = true;

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogService logService;

    @Autowired
    private MockMvc restLogMockMvc;

    private Log log;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Log createEntity() {
        Log log = new Log()
            .url(DEFAULT_URL)
            .requestItem(DEFAULT_REQUEST_ITEM)
            .responseItem(DEFAULT_RESPONSE_ITEM)
            .status(DEFAULT_STATUS)
            .method(DEFAULT_METHOD)
            .platform(DEFAULT_PLATFORM)
            .requestTime(DEFAULT_REQUEST_TIME)
            .responseTime(DEFAULT_RESPONSE_TIME)
            .duration(DEFAULT_DURATION)
            .logged(DEFAULT_LOGGED)
            .user(DEFAULT_USER);
        return log;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Log createUpdatedEntity() {
        Log log = new Log()
            .url(UPDATED_URL)
            .requestItem(UPDATED_REQUEST_ITEM)
            .responseItem(UPDATED_RESPONSE_ITEM)
            .status(UPDATED_STATUS)
            .method(UPDATED_METHOD)
            .platform(UPDATED_PLATFORM)
            .requestTime(UPDATED_REQUEST_TIME)
            .responseTime(UPDATED_RESPONSE_TIME)
            .duration(UPDATED_DURATION)
            .logged(UPDATED_LOGGED)
            .user(UPDATED_USER);
        return log;
    }

    @BeforeEach
    public void initTest() {
        logRepository.deleteAll();
        log = createEntity();
    }

    @Test
    public void createLog() throws Exception {
        int databaseSizeBeforeCreate = logRepository.findAll().size();

        // Create the Log
        LogDTO logDTO = logMapper.toDto(log);
        restLogMockMvc.perform(post("/api/logs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isCreated());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate + 1);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testLog.getRequestItem()).isEqualTo(DEFAULT_REQUEST_ITEM);
        assertThat(testLog.getResponseItem()).isEqualTo(DEFAULT_RESPONSE_ITEM);
        assertThat(testLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLog.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testLog.getPlatform()).isEqualTo(DEFAULT_PLATFORM);
        assertThat(testLog.getRequestTime()).isEqualTo(DEFAULT_REQUEST_TIME);
        assertThat(testLog.getResponseTime()).isEqualTo(DEFAULT_RESPONSE_TIME);
        assertThat(testLog.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testLog.isLogged()).isEqualTo(DEFAULT_LOGGED);
        assertThat(testLog.getUser()).isEqualTo(DEFAULT_USER);
    }

    @Test
    public void createLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logRepository.findAll().size();

        // Create the Log with an existing ID
        log.setId("existing_id");
        LogDTO logDTO = logMapper.toDto(log);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogMockMvc.perform(post("/api/logs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllLogs() throws Exception {
        // Initialize the database
        logRepository.save(log);

        // Get all the logList
        restLogMockMvc.perform(get("/api/logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(log.getId())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].requestItem").value(hasItem(DEFAULT_REQUEST_ITEM)))
            .andExpect(jsonPath("$.[*].responseItem").value(hasItem(DEFAULT_RESPONSE_ITEM)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM)))
            .andExpect(jsonPath("$.[*].requestTime").value(hasItem(DEFAULT_REQUEST_TIME)))
            .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].logged").value(hasItem(DEFAULT_LOGGED.booleanValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)));
    }
    
    @Test
    public void getLog() throws Exception {
        // Initialize the database
        logRepository.save(log);

        // Get the log
        restLogMockMvc.perform(get("/api/logs/{id}", log.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(log.getId()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.requestItem").value(DEFAULT_REQUEST_ITEM))
            .andExpect(jsonPath("$.responseItem").value(DEFAULT_RESPONSE_ITEM))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
            .andExpect(jsonPath("$.platform").value(DEFAULT_PLATFORM))
            .andExpect(jsonPath("$.requestTime").value(DEFAULT_REQUEST_TIME))
            .andExpect(jsonPath("$.responseTime").value(DEFAULT_RESPONSE_TIME))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.logged").value(DEFAULT_LOGGED.booleanValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER));
    }

    @Test
    public void getNonExistingLog() throws Exception {
        // Get the log
        restLogMockMvc.perform(get("/api/logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLog() throws Exception {
        // Initialize the database
        logRepository.save(log);

        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Update the log
        Log updatedLog = logRepository.findById(log.getId()).get();
        updatedLog
            .url(UPDATED_URL)
            .requestItem(UPDATED_REQUEST_ITEM)
            .responseItem(UPDATED_RESPONSE_ITEM)
            .status(UPDATED_STATUS)
            .method(UPDATED_METHOD)
            .platform(UPDATED_PLATFORM)
            .requestTime(UPDATED_REQUEST_TIME)
            .responseTime(UPDATED_RESPONSE_TIME)
            .duration(UPDATED_DURATION)
            .logged(UPDATED_LOGGED)
            .user(UPDATED_USER);
        LogDTO logDTO = logMapper.toDto(updatedLog);

        restLogMockMvc.perform(put("/api/logs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isOk());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testLog.getRequestItem()).isEqualTo(UPDATED_REQUEST_ITEM);
        assertThat(testLog.getResponseItem()).isEqualTo(UPDATED_RESPONSE_ITEM);
        assertThat(testLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testLog.getPlatform()).isEqualTo(UPDATED_PLATFORM);
        assertThat(testLog.getRequestTime()).isEqualTo(UPDATED_REQUEST_TIME);
        assertThat(testLog.getResponseTime()).isEqualTo(UPDATED_RESPONSE_TIME);
        assertThat(testLog.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testLog.isLogged()).isEqualTo(UPDATED_LOGGED);
        assertThat(testLog.getUser()).isEqualTo(UPDATED_USER);
    }

    @Test
    public void updateNonExistingLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Create the Log
        LogDTO logDTO = logMapper.toDto(log);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogMockMvc.perform(put("/api/logs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLog() throws Exception {
        // Initialize the database
        logRepository.save(log);

        int databaseSizeBeforeDelete = logRepository.findAll().size();

        // Delete the log
        restLogMockMvc.perform(delete("/api/logs/{id}", log.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
