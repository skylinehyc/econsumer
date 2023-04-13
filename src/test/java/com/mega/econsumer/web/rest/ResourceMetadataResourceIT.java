package com.mega.econsumer.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.econsumer.IntegrationTest;
import com.mega.econsumer.domain.ResourceMetadata;
import com.mega.econsumer.domain.enumeration.ResourceMetadataType;
import com.mega.econsumer.repository.ResourceMetadataRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceMetadataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceMetadataResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_TYPE = "BBBBBBBBBB";

    private static final ResourceMetadataType DEFAULT_DATA_TYPE = ResourceMetadataType.IMAGE;
    private static final ResourceMetadataType UPDATED_DATA_TYPE = ResourceMetadataType.VEDIO;

    private static final Integer DEFAULT_SHOW_TIME = 1;
    private static final Integer UPDATED_SHOW_TIME = 2;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DATA_PATH = "AAAAAAAAAA";
    private static final String UPDATED_DATA_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_COVER_PIC_PATH = "AAAAAAAAAA";
    private static final String UPDATED_COVER_PIC_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_MD = "AAAAAAAAAA";
    private static final String UPDATED_DATA_MD = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SUFFIX = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/resource-metadata";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceMetadataRepository resourceMetadataRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceMetadataMockMvc;

    private ResourceMetadata resourceMetadata;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceMetadata createEntity(EntityManager em) {
        ResourceMetadata resourceMetadata = new ResourceMetadata()
            .name(DEFAULT_NAME)
            .content(DEFAULT_CONTENT)
            .resourceType(DEFAULT_RESOURCE_TYPE)
            .dataType(DEFAULT_DATA_TYPE)
            .showTime(DEFAULT_SHOW_TIME)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .dataPath(DEFAULT_DATA_PATH)
            .coverPicPath(DEFAULT_COVER_PIC_PATH)
            .dataSize(DEFAULT_DATA_SIZE)
            .dataMd(DEFAULT_DATA_MD)
            .dataSuffix(DEFAULT_DATA_SUFFIX)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return resourceMetadata;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceMetadata createUpdatedEntity(EntityManager em) {
        ResourceMetadata resourceMetadata = new ResourceMetadata()
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return resourceMetadata;
    }

    @BeforeEach
    public void initTest() {
        resourceMetadata = createEntity(em);
    }

    @Test
    @Transactional
    void createResourceMetadata() throws Exception {
        int databaseSizeBeforeCreate = resourceMetadataRepository.findAll().size();
        // Create the ResourceMetadata
        restResourceMetadataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isCreated());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeCreate + 1);
        ResourceMetadata testResourceMetadata = resourceMetadataList.get(resourceMetadataList.size() - 1);
        assertThat(testResourceMetadata.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResourceMetadata.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testResourceMetadata.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
        assertThat(testResourceMetadata.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testResourceMetadata.getShowTime()).isEqualTo(DEFAULT_SHOW_TIME);
        assertThat(testResourceMetadata.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testResourceMetadata.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testResourceMetadata.getDataPath()).isEqualTo(DEFAULT_DATA_PATH);
        assertThat(testResourceMetadata.getCoverPicPath()).isEqualTo(DEFAULT_COVER_PIC_PATH);
        assertThat(testResourceMetadata.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testResourceMetadata.getDataMd()).isEqualTo(DEFAULT_DATA_MD);
        assertThat(testResourceMetadata.getDataSuffix()).isEqualTo(DEFAULT_DATA_SUFFIX);
        assertThat(testResourceMetadata.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testResourceMetadata.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testResourceMetadata.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testResourceMetadata.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testResourceMetadata.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createResourceMetadataWithExistingId() throws Exception {
        // Create the ResourceMetadata with an existing ID
        resourceMetadata.setId(1L);

        int databaseSizeBeforeCreate = resourceMetadataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceMetadataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = resourceMetadataRepository.findAll().size();
        // set the field null
        resourceMetadata.setName(null);

        // Create the ResourceMetadata, which fails.

        restResourceMetadataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllResourceMetadata() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        // Get all the resourceMetadataList
        restResourceMetadataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceMetadata.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].resourceType").value(hasItem(DEFAULT_RESOURCE_TYPE)))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].showTime").value(hasItem(DEFAULT_SHOW_TIME)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].dataPath").value(hasItem(DEFAULT_DATA_PATH)))
            .andExpect(jsonPath("$.[*].coverPicPath").value(hasItem(DEFAULT_COVER_PIC_PATH)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE)))
            .andExpect(jsonPath("$.[*].dataMd").value(hasItem(DEFAULT_DATA_MD)))
            .andExpect(jsonPath("$.[*].dataSuffix").value(hasItem(DEFAULT_DATA_SUFFIX)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getResourceMetadata() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        // Get the resourceMetadata
        restResourceMetadataMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceMetadata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceMetadata.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.resourceType").value(DEFAULT_RESOURCE_TYPE))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE.toString()))
            .andExpect(jsonPath("$.showTime").value(DEFAULT_SHOW_TIME))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.dataPath").value(DEFAULT_DATA_PATH))
            .andExpect(jsonPath("$.coverPicPath").value(DEFAULT_COVER_PIC_PATH))
            .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE))
            .andExpect(jsonPath("$.dataMd").value(DEFAULT_DATA_MD))
            .andExpect(jsonPath("$.dataSuffix").value(DEFAULT_DATA_SUFFIX))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingResourceMetadata() throws Exception {
        // Get the resourceMetadata
        restResourceMetadataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourceMetadata() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();

        // Update the resourceMetadata
        ResourceMetadata updatedResourceMetadata = resourceMetadataRepository.findById(resourceMetadata.getId()).get();
        // Disconnect from session so that the updates on updatedResourceMetadata are not directly saved in db
        em.detach(updatedResourceMetadata);
        updatedResourceMetadata
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restResourceMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResourceMetadata.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedResourceMetadata))
            )
            .andExpect(status().isOk());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
        ResourceMetadata testResourceMetadata = resourceMetadataList.get(resourceMetadataList.size() - 1);
        assertThat(testResourceMetadata.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResourceMetadata.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testResourceMetadata.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testResourceMetadata.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testResourceMetadata.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testResourceMetadata.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResourceMetadata.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testResourceMetadata.getDataPath()).isEqualTo(UPDATED_DATA_PATH);
        assertThat(testResourceMetadata.getCoverPicPath()).isEqualTo(UPDATED_COVER_PIC_PATH);
        assertThat(testResourceMetadata.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testResourceMetadata.getDataMd()).isEqualTo(UPDATED_DATA_MD);
        assertThat(testResourceMetadata.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testResourceMetadata.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testResourceMetadata.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResourceMetadata.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testResourceMetadata.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testResourceMetadata.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourceMetadata.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourceMetadataWithPatch() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();

        // Update the resourceMetadata using partial update
        ResourceMetadata partialUpdatedResourceMetadata = new ResourceMetadata();
        partialUpdatedResourceMetadata.setId(resourceMetadata.getId());

        partialUpdatedResourceMetadata
            .name(UPDATED_NAME)
            .showTime(UPDATED_SHOW_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restResourceMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceMetadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceMetadata))
            )
            .andExpect(status().isOk());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
        ResourceMetadata testResourceMetadata = resourceMetadataList.get(resourceMetadataList.size() - 1);
        assertThat(testResourceMetadata.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResourceMetadata.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testResourceMetadata.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
        assertThat(testResourceMetadata.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testResourceMetadata.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testResourceMetadata.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResourceMetadata.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testResourceMetadata.getDataPath()).isEqualTo(DEFAULT_DATA_PATH);
        assertThat(testResourceMetadata.getCoverPicPath()).isEqualTo(DEFAULT_COVER_PIC_PATH);
        assertThat(testResourceMetadata.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testResourceMetadata.getDataMd()).isEqualTo(DEFAULT_DATA_MD);
        assertThat(testResourceMetadata.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testResourceMetadata.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testResourceMetadata.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResourceMetadata.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testResourceMetadata.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testResourceMetadata.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateResourceMetadataWithPatch() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();

        // Update the resourceMetadata using partial update
        ResourceMetadata partialUpdatedResourceMetadata = new ResourceMetadata();
        partialUpdatedResourceMetadata.setId(resourceMetadata.getId());

        partialUpdatedResourceMetadata
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restResourceMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourceMetadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResourceMetadata))
            )
            .andExpect(status().isOk());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
        ResourceMetadata testResourceMetadata = resourceMetadataList.get(resourceMetadataList.size() - 1);
        assertThat(testResourceMetadata.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResourceMetadata.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testResourceMetadata.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testResourceMetadata.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testResourceMetadata.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testResourceMetadata.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testResourceMetadata.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testResourceMetadata.getDataPath()).isEqualTo(UPDATED_DATA_PATH);
        assertThat(testResourceMetadata.getCoverPicPath()).isEqualTo(UPDATED_COVER_PIC_PATH);
        assertThat(testResourceMetadata.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testResourceMetadata.getDataMd()).isEqualTo(UPDATED_DATA_MD);
        assertThat(testResourceMetadata.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testResourceMetadata.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testResourceMetadata.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResourceMetadata.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testResourceMetadata.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testResourceMetadata.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourceMetadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourceMetadata() throws Exception {
        int databaseSizeBeforeUpdate = resourceMetadataRepository.findAll().size();
        resourceMetadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resourceMetadata))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourceMetadata in the database
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourceMetadata() throws Exception {
        // Initialize the database
        resourceMetadataRepository.saveAndFlush(resourceMetadata);

        int databaseSizeBeforeDelete = resourceMetadataRepository.findAll().size();

        // Delete the resourceMetadata
        restResourceMetadataMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourceMetadata.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResourceMetadata> resourceMetadataList = resourceMetadataRepository.findAll();
        assertThat(resourceMetadataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
