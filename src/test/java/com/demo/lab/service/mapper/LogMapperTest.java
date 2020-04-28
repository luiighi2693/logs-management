package com.demo.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogMapperTest {

    private LogMapper logMapper;

    @BeforeEach
    public void setUp() {
        logMapper = new LogMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(logMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(logMapper.fromId(null)).isNull();
    }
}
