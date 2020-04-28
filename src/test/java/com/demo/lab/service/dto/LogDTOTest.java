package com.demo.lab.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.demo.lab.web.rest.TestUtil;

public class LogDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogDTO.class);
        LogDTO logDTO1 = new LogDTO();
        logDTO1.setId("id1");
        LogDTO logDTO2 = new LogDTO();
        assertThat(logDTO1).isNotEqualTo(logDTO2);
        logDTO2.setId(logDTO1.getId());
        assertThat(logDTO1).isEqualTo(logDTO2);
        logDTO2.setId("id2");
        assertThat(logDTO1).isNotEqualTo(logDTO2);
        logDTO1.setId(null);
        assertThat(logDTO1).isNotEqualTo(logDTO2);
    }
}
