package com.mega.econsumer.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.econsumer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanDetaiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanDetai.class);
        PlanDetai planDetai1 = new PlanDetai();
        planDetai1.setId(1L);
        PlanDetai planDetai2 = new PlanDetai();
        planDetai2.setId(planDetai1.getId());
        assertThat(planDetai1).isEqualTo(planDetai2);
        planDetai2.setId(2L);
        assertThat(planDetai1).isNotEqualTo(planDetai2);
        planDetai1.setId(null);
        assertThat(planDetai1).isNotEqualTo(planDetai2);
    }
}
