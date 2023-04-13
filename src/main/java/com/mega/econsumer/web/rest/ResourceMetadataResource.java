package com.mega.econsumer.web.rest;

import com.mega.econsumer.domain.ResourceMetadata;
import com.mega.econsumer.repository.ResourceMetadataRepository;
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
 * REST controller for managing {@link com.mega.econsumer.domain.ResourceMetadata}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ResourceMetadataResource {

    private final Logger log = LoggerFactory.getLogger(ResourceMetadataResource.class);

    private static final String ENTITY_NAME = "resourceMetadata";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceMetadataRepository resourceMetadataRepository;

    public ResourceMetadataResource(ResourceMetadataRepository resourceMetadataRepository) {
        this.resourceMetadataRepository = resourceMetadataRepository;
    }

    /**
     * {@code POST  /resource-metadata} : Create a new resourceMetadata.
     *
     * @param resourceMetadata the resourceMetadata to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceMetadata, or with status {@code 400 (Bad Request)} if the resourceMetadata has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-metadata")
    public ResponseEntity<ResourceMetadata> createResourceMetadata(@Valid @RequestBody ResourceMetadata resourceMetadata)
        throws URISyntaxException {
        log.debug("REST request to save ResourceMetadata : {}", resourceMetadata);
        if (resourceMetadata.getId() != null) {
            throw new BadRequestAlertException("A new resourceMetadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceMetadata result = resourceMetadataRepository.save(resourceMetadata);
        return ResponseEntity
            .created(new URI("/api/resource-metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-metadata/:id} : Updates an existing resourceMetadata.
     *
     * @param id the id of the resourceMetadata to save.
     * @param resourceMetadata the resourceMetadata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceMetadata,
     * or with status {@code 400 (Bad Request)} if the resourceMetadata is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceMetadata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-metadata/{id}")
    public ResponseEntity<ResourceMetadata> updateResourceMetadata(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ResourceMetadata resourceMetadata
    ) throws URISyntaxException {
        log.debug("REST request to update ResourceMetadata : {}, {}", id, resourceMetadata);
        if (resourceMetadata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceMetadata.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceMetadataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResourceMetadata result = resourceMetadataRepository.save(resourceMetadata);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, resourceMetadata.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /resource-metadata/:id} : Partial updates given fields of an existing resourceMetadata, field will ignore if it is null
     *
     * @param id the id of the resourceMetadata to save.
     * @param resourceMetadata the resourceMetadata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceMetadata,
     * or with status {@code 400 (Bad Request)} if the resourceMetadata is not valid,
     * or with status {@code 404 (Not Found)} if the resourceMetadata is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourceMetadata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/resource-metadata/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResourceMetadata> partialUpdateResourceMetadata(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ResourceMetadata resourceMetadata
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResourceMetadata partially : {}, {}", id, resourceMetadata);
        if (resourceMetadata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceMetadata.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceMetadataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResourceMetadata> result = resourceMetadataRepository
            .findById(resourceMetadata.getId())
            .map(existingResourceMetadata -> {
                if (resourceMetadata.getName() != null) {
                    existingResourceMetadata.setName(resourceMetadata.getName());
                }
                if (resourceMetadata.getContent() != null) {
                    existingResourceMetadata.setContent(resourceMetadata.getContent());
                }
                if (resourceMetadata.getResourceType() != null) {
                    existingResourceMetadata.setResourceType(resourceMetadata.getResourceType());
                }
                if (resourceMetadata.getDataType() != null) {
                    existingResourceMetadata.setDataType(resourceMetadata.getDataType());
                }
                if (resourceMetadata.getShowTime() != null) {
                    existingResourceMetadata.setShowTime(resourceMetadata.getShowTime());
                }
                if (resourceMetadata.getStartTime() != null) {
                    existingResourceMetadata.setStartTime(resourceMetadata.getStartTime());
                }
                if (resourceMetadata.getEndTime() != null) {
                    existingResourceMetadata.setEndTime(resourceMetadata.getEndTime());
                }
                if (resourceMetadata.getDataPath() != null) {
                    existingResourceMetadata.setDataPath(resourceMetadata.getDataPath());
                }
                if (resourceMetadata.getCoverPicPath() != null) {
                    existingResourceMetadata.setCoverPicPath(resourceMetadata.getCoverPicPath());
                }
                if (resourceMetadata.getDataSize() != null) {
                    existingResourceMetadata.setDataSize(resourceMetadata.getDataSize());
                }
                if (resourceMetadata.getDataMd() != null) {
                    existingResourceMetadata.setDataMd(resourceMetadata.getDataMd());
                }
                if (resourceMetadata.getDataSuffix() != null) {
                    existingResourceMetadata.setDataSuffix(resourceMetadata.getDataSuffix());
                }
                if (resourceMetadata.getRemark() != null) {
                    existingResourceMetadata.setRemark(resourceMetadata.getRemark());
                }
                if (resourceMetadata.getCreatedBy() != null) {
                    existingResourceMetadata.setCreatedBy(resourceMetadata.getCreatedBy());
                }
                if (resourceMetadata.getCreatedDate() != null) {
                    existingResourceMetadata.setCreatedDate(resourceMetadata.getCreatedDate());
                }
                if (resourceMetadata.getLastModifiedBy() != null) {
                    existingResourceMetadata.setLastModifiedBy(resourceMetadata.getLastModifiedBy());
                }
                if (resourceMetadata.getLastModifiedDate() != null) {
                    existingResourceMetadata.setLastModifiedDate(resourceMetadata.getLastModifiedDate());
                }

                return existingResourceMetadata;
            })
            .map(resourceMetadataRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, resourceMetadata.getId().toString())
        );
    }

    /**
     * {@code GET  /resource-metadata} : get all the resourceMetadata.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceMetadata in body.
     */
    @GetMapping("/resource-metadata")
    public List<ResourceMetadata> getAllResourceMetadata() {
        log.debug("REST request to get all ResourceMetadata");
        return resourceMetadataRepository.findAll();
    }

    /**
     * {@code GET  /resource-metadata/:id} : get the "id" resourceMetadata.
     *
     * @param id the id of the resourceMetadata to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceMetadata, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-metadata/{id}")
    public ResponseEntity<ResourceMetadata> getResourceMetadata(@PathVariable Long id) {
        log.debug("REST request to get ResourceMetadata : {}", id);
        Optional<ResourceMetadata> resourceMetadata = resourceMetadataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resourceMetadata);
    }

    /**
     * {@code DELETE  /resource-metadata/:id} : delete the "id" resourceMetadata.
     *
     * @param id the id of the resourceMetadata to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-metadata/{id}")
    public ResponseEntity<Void> deleteResourceMetadata(@PathVariable Long id) {
        log.debug("REST request to delete ResourceMetadata : {}", id);
        resourceMetadataRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
