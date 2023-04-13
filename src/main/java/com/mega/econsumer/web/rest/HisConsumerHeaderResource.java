package com.mega.econsumer.web.rest;

import com.mega.econsumer.domain.HisConsumerHeader;
import com.mega.econsumer.repository.HisConsumerHeaderRepository;
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
 * REST controller for managing {@link com.mega.econsumer.domain.HisConsumerHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HisConsumerHeaderResource {

    private final Logger log = LoggerFactory.getLogger(HisConsumerHeaderResource.class);

    private static final String ENTITY_NAME = "hisConsumerHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HisConsumerHeaderRepository hisConsumerHeaderRepository;

    public HisConsumerHeaderResource(HisConsumerHeaderRepository hisConsumerHeaderRepository) {
        this.hisConsumerHeaderRepository = hisConsumerHeaderRepository;
    }

    /**
     * {@code POST  /his-consumer-headers} : Create a new hisConsumerHeader.
     *
     * @param hisConsumerHeader the hisConsumerHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hisConsumerHeader, or with status {@code 400 (Bad Request)} if the hisConsumerHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/his-consumer-headers")
    public ResponseEntity<HisConsumerHeader> createHisConsumerHeader(@Valid @RequestBody HisConsumerHeader hisConsumerHeader)
        throws URISyntaxException {
        log.debug("REST request to save HisConsumerHeader : {}", hisConsumerHeader);
        if (hisConsumerHeader.getId() != null) {
            throw new BadRequestAlertException("A new hisConsumerHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HisConsumerHeader result = hisConsumerHeaderRepository.save(hisConsumerHeader);
        return ResponseEntity
            .created(new URI("/api/his-consumer-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /his-consumer-headers/:id} : Updates an existing hisConsumerHeader.
     *
     * @param id the id of the hisConsumerHeader to save.
     * @param hisConsumerHeader the hisConsumerHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hisConsumerHeader,
     * or with status {@code 400 (Bad Request)} if the hisConsumerHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hisConsumerHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/his-consumer-headers/{id}")
    public ResponseEntity<HisConsumerHeader> updateHisConsumerHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HisConsumerHeader hisConsumerHeader
    ) throws URISyntaxException {
        log.debug("REST request to update HisConsumerHeader : {}, {}", id, hisConsumerHeader);
        if (hisConsumerHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hisConsumerHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hisConsumerHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HisConsumerHeader result = hisConsumerHeaderRepository.save(hisConsumerHeader);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hisConsumerHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /his-consumer-headers/:id} : Partial updates given fields of an existing hisConsumerHeader, field will ignore if it is null
     *
     * @param id the id of the hisConsumerHeader to save.
     * @param hisConsumerHeader the hisConsumerHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hisConsumerHeader,
     * or with status {@code 400 (Bad Request)} if the hisConsumerHeader is not valid,
     * or with status {@code 404 (Not Found)} if the hisConsumerHeader is not found,
     * or with status {@code 500 (Internal Server Error)} if the hisConsumerHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/his-consumer-headers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HisConsumerHeader> partialUpdateHisConsumerHeader(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HisConsumerHeader hisConsumerHeader
    ) throws URISyntaxException {
        log.debug("REST request to partial update HisConsumerHeader partially : {}, {}", id, hisConsumerHeader);
        if (hisConsumerHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hisConsumerHeader.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hisConsumerHeaderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HisConsumerHeader> result = hisConsumerHeaderRepository
            .findById(hisConsumerHeader.getId())
            .map(existingHisConsumerHeader -> {
                if (hisConsumerHeader.getBusinessId() != null) {
                    existingHisConsumerHeader.setBusinessId(hisConsumerHeader.getBusinessId());
                }
                if (hisConsumerHeader.getBusinessType() != null) {
                    existingHisConsumerHeader.setBusinessType(hisConsumerHeader.getBusinessType());
                }
                if (hisConsumerHeader.getFacId() != null) {
                    existingHisConsumerHeader.setFacId(hisConsumerHeader.getFacId());
                }
                if (hisConsumerHeader.getAreaId() != null) {
                    existingHisConsumerHeader.setAreaId(hisConsumerHeader.getAreaId());
                }
                if (hisConsumerHeader.getPlanCode() != null) {
                    existingHisConsumerHeader.setPlanCode(hisConsumerHeader.getPlanCode());
                }
                if (hisConsumerHeader.getPlanName() != null) {
                    existingHisConsumerHeader.setPlanName(hisConsumerHeader.getPlanName());
                }
                if (hisConsumerHeader.getPlanStartTime() != null) {
                    existingHisConsumerHeader.setPlanStartTime(hisConsumerHeader.getPlanStartTime());
                }
                if (hisConsumerHeader.getPlanEndTime() != null) {
                    existingHisConsumerHeader.setPlanEndTime(hisConsumerHeader.getPlanEndTime());
                }
                if (hisConsumerHeader.getCreatedBy() != null) {
                    existingHisConsumerHeader.setCreatedBy(hisConsumerHeader.getCreatedBy());
                }
                if (hisConsumerHeader.getCreatedDate() != null) {
                    existingHisConsumerHeader.setCreatedDate(hisConsumerHeader.getCreatedDate());
                }
                if (hisConsumerHeader.getLastModifiedBy() != null) {
                    existingHisConsumerHeader.setLastModifiedBy(hisConsumerHeader.getLastModifiedBy());
                }
                if (hisConsumerHeader.getLastModifiedDate() != null) {
                    existingHisConsumerHeader.setLastModifiedDate(hisConsumerHeader.getLastModifiedDate());
                }

                return existingHisConsumerHeader;
            })
            .map(hisConsumerHeaderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hisConsumerHeader.getId().toString())
        );
    }

    /**
     * {@code GET  /his-consumer-headers} : get all the hisConsumerHeaders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hisConsumerHeaders in body.
     */
    @GetMapping("/his-consumer-headers")
    public List<HisConsumerHeader> getAllHisConsumerHeaders() {
        log.debug("REST request to get all HisConsumerHeaders");
        return hisConsumerHeaderRepository.findAll();
    }

    /**
     * {@code GET  /his-consumer-headers/:id} : get the "id" hisConsumerHeader.
     *
     * @param id the id of the hisConsumerHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hisConsumerHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/his-consumer-headers/{id}")
    public ResponseEntity<HisConsumerHeader> getHisConsumerHeader(@PathVariable Long id) {
        log.debug("REST request to get HisConsumerHeader : {}", id);
        Optional<HisConsumerHeader> hisConsumerHeader = hisConsumerHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hisConsumerHeader);
    }

    /**
     * {@code DELETE  /his-consumer-headers/:id} : delete the "id" hisConsumerHeader.
     *
     * @param id the id of the hisConsumerHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/his-consumer-headers/{id}")
    public ResponseEntity<Void> deleteHisConsumerHeader(@PathVariable Long id) {
        log.debug("REST request to delete HisConsumerHeader : {}", id);
        hisConsumerHeaderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
