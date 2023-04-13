package com.mega.econsumer.repository;

import com.mega.econsumer.domain.HisConsumerHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HisConsumerHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HisConsumerHeaderRepository extends JpaRepository<HisConsumerHeader, Long> {}
