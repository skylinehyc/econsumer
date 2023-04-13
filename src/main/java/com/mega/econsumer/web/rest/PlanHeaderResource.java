package com.mega.econsumer.web.rest;

import com.mega.econsumer.domain.PlanHeader;
import com.mega.econsumer.repository.PlanHeaderRepository;
import com.mega.econsumer.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mega.econsumer.domain.PlanHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlanHeaderResource {

    private final Logger log = LoggerFactory.getLogger(PlanHeaderResource.class);

    private static final String ENTITY_NAME = "planHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanHeaderRepository planHeaderRepository;

    public PlanHeaderResource(PlanHeaderRepository planHeaderRepository) {
        this.planHeaderRepository = planHeaderRepository;
    }

    /**
     * {@code POST  /plan-headers} : Create a new planHeader.
     *
     * @param planHeader the planHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planHeader, or with status {@code 400 (Bad Request)} if the planHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-headers")
    public ResponseEntity<PlanHeader> createPlanHeader(@Valid @RequestBody PlanHeader planHeader) throws URISyntaxException {
        log.debug("REST request to save PlanHeader : {}", planHeader);
        if (planHeader.getId() != null) {
            throw new BadRequestAlertException("A new planHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanHeader result = planHeaderRepository.save(planHeader);
        return ResponseEntity
            .created(new URI("/api/plan-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-headers/:id} : Updates an existing planHeader.
     *
     * @param id the id of the planHeader to save.
     * @param planHeader the planHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planHeader,
     * or with status {@code 400 (Bad Request)} if the planHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-headers/{id}")
    public ResponseEntity<PlanHeader> updatePlanHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PlanHeader planHeader
    ) throws URISyntaxException {
        log.debug("REST request to update PlanHeader : {}, {}", id, planHeader);
        if (planHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PlanHeader result = planHeaderRepository.save(planHeader);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /plan-headers/:id} : Partial updates given fields of an existing planHeader, field will ignore if it is null
     *
     * @param id the id of the planHeader to save.
     * @param planHeader the planHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planHeader,
     * or with status {@code 400 (Bad Request)} if the planHeader is not valid,
     * or with status {@code 404 (Not Found)} if the planHeader is not found,
     * or with status {@code 500 (Internal Server Error)} if the planHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/plan-headers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PlanHeader> partialUpdatePlanHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PlanHeader planHeader
    ) throws URISyntaxException {
        log.debug("REST request to partial update PlanHeader partially : {}, {}", id, planHeader);
        if (planHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PlanHeader> result = planHeaderRepository
            .findById(planHeader.getId())
            .map(existingPlanHeader -> {
                if (planHeader.getPlanCode() != null) {
                    existingPlanHeader.setPlanCode(planHeader.getPlanCode());
                }
                if (planHeader.getPlanName() != null) {
                    existingPlanHeader.setPlanName(planHeader.getPlanName());
                }
                if (planHeader.getPlanState() != null) {
                    existingPlanHeader.setPlanState(planHeader.getPlanState());
                }
                if (planHeader.getPlanStartTime() != null) {
                    existingPlanHeader.setPlanStartTime(planHeader.getPlanStartTime());
                }
                if (planHeader.getPlanEndTime() != null) {
                    existingPlanHeader.setPlanEndTime(planHeader.getPlanEndTime());
                }
                if (planHeader.getRemark() != null) {
                    existingPlanHeader.setRemark(planHeader.getRemark());
                }
                if (planHeader.getCreatedBy() != null) {
                    existingPlanHeader.setCreatedBy(planHeader.getCreatedBy());
                }
                if (planHeader.getCreatedDate() != null) {
                    existingPlanHeader.setCreatedDate(planHeader.getCreatedDate());
                }
                if (planHeader.getLastModifiedBy() != null) {
                    existingPlanHeader.setLastModifiedBy(planHeader.getLastModifiedBy());
                }
                if (planHeader.getLastModifiedDate() != null) {
                    existingPlanHeader.setLastModifiedDate(planHeader.getLastModifiedDate());
                }

                return existingPlanHeader;
            })
            .map(planHeaderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planHeader.getId().toString())
        );
    }

    /**
     * {@code GET  /plan-headers} : get all the planHeaders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planHeaders in body.
     */
    @GetMapping("/plan-headers")
    public List<PlanHeader> getAllPlanHeaders() {
        log.debug("REST request to get all PlanHeaders");
        return planHeaderRepository.findAll();
    }

    /**
     * {@code GET  /plan-headers/:id} : get the "id" planHeader.
     *
     * @param id the id of the planHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-headers/{id}")
    public ResponseEntity<PlanHeader> getPlanHeader(@PathVariable Long id) {
        log.debug("REST request to get PlanHeader : {}", id);
        Optional<PlanHeader> planHeader = planHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planHeader);
    }

    /**
     * {@code DELETE  /plan-headers/:id} : delete the "id" planHeader.
     *
     * @param id the id of the planHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-headers/{id}")
    public ResponseEntity<Void> deletePlanHeader(@PathVariable Long id) {
        log.debug("REST request to delete PlanHeader : {}", id);
        planHeaderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
