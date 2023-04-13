package com.mega.econsumer.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.econsumer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanHeaderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanHeader.class);
        PlanHeader planHeader1 = new PlanHeader();
        planHeader1.setId(1L);
        PlanHeader planHeader2 = new PlanHeader();
        planHeader2.setId(planHeader1.getId());
        assertThat(planHeader1).isEqualTo(planHeader2);
        planHeader2.setId(2L);
        assertThat(planHeader1).isNotEqualTo(planHeader2);
        planHeader1.setId(null);
        assertThat(planHeader1).isNotEqualTo(planHeader2);
    }
}
