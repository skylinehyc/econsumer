package com.mega.econsumer.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.econsumer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceMetadataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceMetadata.class);
        ResourceMetadata resourceMetadata1 = new ResourceMetadata();
        resourceMetadata1.setId(1L);
        ResourceMetadata resourceMetadata2 = new ResourceMetadata();
        resourceMetadata2.setId(resourceMetadata1.getId());
        assertThat(resourceMetadata1).isEqualTo(resourceMetadata2);
        resourceMetadata2.setId(2L);
        assertThat(resourceMetadata1).isNotEqualTo(resourceMetadata2);
        resourceMetadata1.setId(null);
        assertThat(resourceMetadata1).isNotEqualTo(resourceMetadata2);
    }
}
