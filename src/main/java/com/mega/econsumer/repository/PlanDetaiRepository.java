package com.mega.econsumer.repository;

import com.mega.econsumer.domain.PlanDetai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PlanDetai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanDetaiRepository extends JpaRepository<PlanDetai, Long> {}
