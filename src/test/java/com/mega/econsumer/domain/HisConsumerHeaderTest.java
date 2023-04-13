package com.mega.econsumer.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.econsumer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HisConsumerHeaderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HisConsumerHeader.class);
        HisConsumerHeader hisConsumerHeader1 = new HisConsumerHeader();
        hisConsumerHeader1.setId(1L);
        HisConsumerHeader hisConsumerHeader2 = new HisConsumerHeader();
        hisConsumerHeader2.setId(hisConsumerHeader1.getId());
        assertThat(hisConsumerHeader1).isEqualTo(hisConsumerHeader2);
        hisConsumerHeader2.setId(2L);
        assertThat(hisConsumerHeader1).isNotEqualTo(hisConsumerHeader2);
        hisConsumerHeader1.setId(null);
        assertThat(hisConsumerHeader1).isNotEqualTo(hisConsumerHeader2);
    }
}
