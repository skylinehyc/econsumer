package com.mega.econsumer.repository;

import com.mega.econsumer.domain.PlanHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PlanHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanHeaderRepository extends JpaRepository<PlanHeader, Long> {}
