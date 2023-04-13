package com.mega.econsumer.web.rest;

import com.mega.econsumer.domain.HisConsumerDetai;
import com.mega.econsumer.repository.HisConsumerDetaiRepository;
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
 * REST controller for managing {@link com.mega.econsumer.domain.HisConsumerDetai}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HisConsumerDetaiResource {

    private final Logger log = LoggerFactory.getLogger(HisConsumerDetaiResource.class);

    private static final String ENTITY_NAME = "hisConsumerDetai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HisConsumerDetaiRepository hisConsumerDetaiRepository;

    public HisConsumerDetaiResource(HisConsumerDetaiRepository hisConsumerDetaiRepository) {
        this.hisConsumerDetaiRepository = hisConsumerDetaiRepository;
    }

    /**
     * {@code POST  /his-consumer-detais} : Create a new hisConsumerDetai.
     *
     * @param hisConsumerDetai the hisConsumerDetai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hisConsumerDetai, or with status {@code 400 (Bad Request)} if the hisConsumerDetai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/his-consumer-detais")
    public ResponseEntity<HisConsumerDetai> createHisConsumerDetai(@Valid @RequestBody HisConsumerDetai hisConsumerDetai)
        throws URISyntaxException {
        log.debug("REST request to save HisConsumerDetai : {}", hisConsumerDetai);
        if (hisConsumerDetai.getId() != null) {
            throw new BadRequestAlertException("A new hisConsumerDetai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HisConsumerDetai result = hisConsumerDetaiRepository.save(hisConsumerDetai);
        return ResponseEntity
            .created(new URI("/api/his-consumer-detais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /his-consumer-detais/:id} : Updates an existing hisConsumerDetai.
     *
     * @param id the id of the hisConsumerDetai to save.
     * @param hisConsumerDetai the hisConsumerDetai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hisConsumerDetai,
     * or with status {@code 400 (Bad Request)} if the hisConsumerDetai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hisConsumerDetai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/his-consumer-detais/{id}")
    public ResponseEntity<HisConsumerDetai> updateHisConsumerDetai(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HisConsumerDetai hisConsumerDetai
    ) throws URISyntaxException {
        log.debug("REST request to update HisConsumerDetai : {}, {}", id, hisConsumerDetai);
        if (hisConsumerDetai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hisConsumerDetai.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hisConsumerDetaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HisConsumerDetai result = hisConsumerDetaiRepository.save(hisConsumerDetai);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hisConsumerDetai.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /his-consumer-detais/:id} : Partial updates given fields of an existing hisConsumerDetai, field will ignore if it is null
     *
     * @param id the id of the hisConsumerDetai to save.
     * @param hisConsumerDetai the hisConsumerDetai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hisConsumerDetai,
     * or with status {@code 400 (Bad Request)} if the hisConsumerDetai is not valid,
     * or with status {@code 404 (Not Found)} if the hisConsumerDetai is not found,
     * or with status {@code 500 (Internal Server Error)} if the hisConsumerDetai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/his-consumer-detais/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HisConsumerDetai> partialUpdateHisConsumerDetai(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HisConsumerDetai hisConsumerDetai
    ) throws URISyntaxException {
        log.debug("REST request to partial update HisConsumerDetai partially : {}, {}", id, hisConsumerDetai);
        if (hisConsumerDetai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hisConsumerDetai.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hisConsumerDetaiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HisConsumerDetai> result = hisConsumerDetaiRepository
            .findById(hisConsumerDetai.getId())
            .map(existingHisConsumerDetai -> {
                if (hisConsumerDetai.getResourceId() != null) {
                    existingHisConsumerDetai.setResourceId(hisConsumerDetai.getResourceId());
                }
                if (hisConsumerDetai.getName() != null) {
                    existingHisConsumerDetai.setName(hisConsumerDetai.getName());
                }
                if (hisConsumerDetai.getContent() != null) {
                    existingHisConsumerDetai.setContent(hisConsumerDetai.getContent());
                }
                if (hisConsumerDetai.getResourceType() != null) {
                    existingHisConsumerDetai.setResourceType(hisConsumerDetai.getResourceType());
                }
                if (hisConsumerDetai.getDataType() != null) {
                    existingHisConsumerDetai.setDataType(hisConsumerDetai.getDataType());
                }
                if (hisConsumerDetai.getShowTime() != null) {
                    existingHisConsumerDetai.setShowTime(hisConsumerDetai.getShowTime());
                }
                if (hisConsumerDetai.getDataPath() != null) {
                    existingHisConsumerDetai.setDataPath(hisConsumerDetai.getDataPath());
                }
                if (hisConsumerDetai.getCoverPicPath() != null) {
                    existingHisConsumerDetai.setCoverPicPath(hisConsumerDetai.getCoverPicPath());
                }
                if (hisConsumerDetai.getDataSize() != null) {
                    existingHisConsumerDetai.setDataSize(hisConsumerDetai.getDataSize());
                }
                if (hisConsumerDetai.getDataMd() != null) {
                    existingHisConsumerDetai.setDataMd(hisConsumerDetai.getDataMd());
                }
                if (hisConsumerDetai.getDataSuffix() != null) {
                    existingHisConsumerDetai.setDataSuffix(hisConsumerDetai.getDataSuffix());
                }
                if (hisConsumerDetai.getRemark() != null) {
                    existingHisConsumerDetai.setRemark(hisConsumerDetai.getRemark());
                }
                if (hisConsumerDetai.getPlanTime() != null) {
                    existingHisConsumerDetai.setPlanTime(hisConsumerDetai.getPlanTime());
                }
                if (hisConsumerDetai.getCreatedBy() != null) {
                    existingHisConsumerDetai.setCreatedBy(hisConsumerDetai.getCreatedBy());
                }
                if (hisConsumerDetai.getCreatedDate() != null) {
                    existingHisConsumerDetai.setCreatedDate(hisConsumerDetai.getCreatedDate());
                }
                if (hisConsumerDetai.getLastModifiedBy() != null) {
                    existingHisConsumerDetai.setLastModifiedBy(hisConsumerDetai.getLastModifiedBy());
                }
                if (hisConsumerDetai.getLastModifiedDate() != null) {
                    existingHisConsumerDetai.setLastModifiedDate(hisConsumerDetai.getLastModifiedDate());
                }

                return existingHisConsumerDetai;
            })
            .map(hisConsumerDetaiRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hisConsumerDetai.getId().toString())
        );
    }

    /**
     * {@code GET  /his-consumer-detais} : get all the hisConsumerDetais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hisConsumerDetais in body.
     */
    @GetMapping("/his-consumer-detais")
    public List<HisConsumerDetai> getAllHisConsumerDetais() {
        log.debug("REST request to get all HisConsumerDetais");
        return hisConsumerDetaiRepository.findAll();
    }

    /**
     * {@code GET  /his-consumer-detais/:id} : get the "id" hisConsumerDetai.
     *
     * @param id the id of the hisConsumerDetai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hisConsumerDetai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/his-consumer-detais/{id}")
    public ResponseEntity<HisConsumerDetai> getHisConsumerDetai(@PathVariable Long id) {
        log.debug("REST request to get HisConsumerDetai : {}", id);
        Optional<HisConsumerDetai> hisConsumerDetai = hisConsumerDetaiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hisConsumerDetai);
    }

    /**
     * {@code DELETE  /his-consumer-detais/:id} : delete the "id" hisConsumerDetai.
     *
     * @param id the id of the hisConsumerDetai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/his-consumer-detais/{id}")
    public ResponseEntity<Void> deleteHisConsumerDetai(@PathVariable Long id) {
        log.debug("REST request to delete HisConsumerDetai : {}", id);
        hisConsumerDetaiRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
