package com.mega.econsumer.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.econsumer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HisConsumerDetaiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HisConsumerDetai.class);
        HisConsumerDetai hisConsumerDetai1 = new HisConsumerDetai();
        hisConsumerDetai1.setId(1L);
        HisConsumerDetai hisConsumerDetai2 = new HisConsumerDetai();
        hisConsumerDetai2.setId(hisConsumerDetai1.getId());
        assertThat(hisConsumerDetai1).isEqualTo(hisConsumerDetai2);
        hisConsumerDetai2.setId(2L);
        assertThat(hisConsumerDetai1).isNotEqualTo(hisConsumerDetai2);
        hisConsumerDetai1.setId(null);
        assertThat(hisConsumerDetai1).isNotEqualTo(hisConsumerDetai2);
    }
}
