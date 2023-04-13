package com.mega.econsumer.repository;

import com.mega.econsumer.domain.HisConsumerDetai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HisConsumerDetai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HisConsumerDetaiRepository extends JpaRepository<HisConsumerDetai, Long> {}
