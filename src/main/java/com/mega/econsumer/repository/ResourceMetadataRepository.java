package com.mega.econsumer.repository;

import com.mega.econsumer.domain.ResourceMetadata;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResourceMetadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceMetadataRepository extends JpaRepository<ResourceMetadata, Long> {}
