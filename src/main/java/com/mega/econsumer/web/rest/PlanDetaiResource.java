package com.mega.econsumer.web.rest;

import com.mega.econsumer.domain.PlanDetai;
import com.mega.econsumer.repository.PlanDetaiRepository;
import com.mega.econsumer.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mega.econsumer.domain.PlanDetai}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlanDetaiResource {

    private final Logger log = LoggerFactory.getLogger(PlanDetaiResource.class);

    private static final String ENTITY_NAME = "planDetai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanDetaiRepository planDetaiRepository;

    public PlanDetaiResource(PlanDetaiRepository planDetaiRepository) {
        this.planDetaiRepository = planDetaiRepository;
    }

    /**
     * {@code POST  /plan-detais} : Create a new planDetai.
     *
     * @param planDetai the planDetai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planDetai, or with status {@code 400 (Bad Request)} if the planDetai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-detais")
    public ResponseEntity<PlanDetai> createPlanDetai(@RequestBody PlanDetai planDetai) throws URISyntaxException {
        log.debug("REST request to save PlanDetai : {}", planDetai);
        if (planDetai.getId() != null) {
            throw new BadRequestAlertException("A new planDetai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanDetai result = planDetaiRepository.save(planDetai);
        return ResponseEntity
            .created(new URI("/api/plan-detais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-detais/:id} : Updates an existing planDetai.
     *
     * @param id the id of the planDetai to save.
     * @param planDetai the planDetai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planDetai,
     * or with status {@code 400 (Bad Request)} if the planDetai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planDetai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-detais/{id}")
    public ResponseEntity<PlanDetai> updatePlanDetai(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PlanDetai planDetai
    ) throws URISyntaxException {
        log.debug("REST request to update PlanDetai : {}, {}", id, planDetai);
        if (planDetai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planDetai.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planDetaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PlanDetai result = planDetaiRepository.save(planDetai);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planDetai.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /plan-detais/:id} : Partial updates given fields of an existing planDetai, field will ignore if it is null
     *
     * @param id the id of the planDetai to save.
     * @param planDetai the planDetai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planDetai,
     * or with status {@code 400 (Bad Request)} if the planDetai is not valid,
     * or with status {@code 404 (Not Found)} if the planDetai is not found,
     * or with status {@code 500 (Internal Server Error)} if the planDetai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/plan-detais/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PlanDetai> partialUpdatePlanDetai(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PlanDetai planDetai
    ) throws URISyntaxException {
        log.debug("REST request to partial update PlanDetai partially : {}, {}", id, planDetai);
        if (planDetai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planDetai.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planDetaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PlanDetai> result = planDetaiRepository
            .findById(planDetai.getId())
            .map(existingPlanDetai -> {
                if (planDetai.getPlanTime() != null) {
                    existingPlanDetai.setPlanTime(planDetai.getPlanTime());
                }
                if (planDetai.getCreatedBy() != null) {
                    existingPlanDetai.setCreatedBy(planDetai.getCreatedBy());
                }
                if (planDetai.getCreatedDate() != null) {
                    existingPlanDetai.setCreatedDate(planDetai.getCreatedDate());
                }
                if (planDetai.getLastModifiedBy() != null) {
                    existingPlanDetai.setLastModifiedBy(planDetai.getLastModifiedBy());
                }
                if (planDetai.getLastModifiedDate() != null) {
                    existingPlanDetai.setLastModifiedDate(planDetai.getLastModifiedDate());
                }

                return existingPlanDetai;
            })
            .map(planDetaiRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planDetai.getId().toString())
        );
    }

    /**
     * {@code GET  /plan-detais} : get all the planDetais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planDetais in body.
     */
    @GetMapping("/plan-detais")
    public List<PlanDetai> getAllPlanDetais() {
        log.debug("REST request to get all PlanDetais");
        return planDetaiRepository.findAll();
    }

    /**
     * {@code GET  /plan-detais/:id} : get the "id" planDetai.
     *
     * @param id the id of the planDetai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planDetai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-detais/{id}")
    public ResponseEntity<PlanDetai> getPlanDetai(@PathVariable Long id) {
        log.debug("REST request to get PlanDetai : {}", id);
        Optional<PlanDetai> planDetai = planDetaiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planDetai);
    }

    /**
     * {@code DELETE  /plan-detais/:id} : delete the "id" planDetai.
     *
     * @param id the id of the planDetai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-detais/{id}")
    public ResponseEntity<Void> deletePlanDetai(@PathVariable Long id) {
        log.debug("REST request to delete PlanDetai : {}", id);
        planDetaiRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
