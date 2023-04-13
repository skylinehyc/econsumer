package com.mega.econsumer.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.econsumer.IntegrationTest;
import com.mega.econsumer.domain.HisConsumerDetai;
import com.mega.econsumer.domain.enumeration.ResourceMetadataType;
import com.mega.econsumer.repository.HisConsumerDetaiRepository;
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
 * Integration tests for the {@link HisConsumerDetaiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HisConsumerDetaiResourceIT {

    private static final String DEFAULT_RESOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_PLAN_TIME = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/his-consumer-detais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HisConsumerDetaiRepository hisConsumerDetaiRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHisConsumerDetaiMockMvc;

    private HisConsumerDetai hisConsumerDetai;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HisConsumerDetai createEntity(EntityManager em) {
        HisConsumerDetai hisConsumerDetai = new HisConsumerDetai()
            .resourceId(DEFAULT_RESOURCE_ID)
            .name(DEFAULT_NAME)
            .content(DEFAULT_CONTENT)
            .resourceType(DEFAULT_RESOURCE_TYPE)
            .dataType(DEFAULT_DATA_TYPE)
            .showTime(DEFAULT_SHOW_TIME)
            .dataPath(DEFAULT_DATA_PATH)
            .coverPicPath(DEFAULT_COVER_PIC_PATH)
            .dataSize(DEFAULT_DATA_SIZE)
            .dataMd(DEFAULT_DATA_MD)
            .dataSuffix(DEFAULT_DATA_SUFFIX)
            .remark(DEFAULT_REMARK)
            .planTime(DEFAULT_PLAN_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return hisConsumerDetai;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HisConsumerDetai createUpdatedEntity(EntityManager em) {
        HisConsumerDetai hisConsumerDetai = new HisConsumerDetai()
            .resourceId(UPDATED_RESOURCE_ID)
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return hisConsumerDetai;
    }

    @BeforeEach
    public void initTest() {
        hisConsumerDetai = createEntity(em);
    }

    @Test
    @Transactional
    void createHisConsumerDetai() throws Exception {
        int databaseSizeBeforeCreate = hisConsumerDetaiRepository.findAll().size();
        // Create the HisConsumerDetai
        restHisConsumerDetaiMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isCreated());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeCreate + 1);
        HisConsumerDetai testHisConsumerDetai = hisConsumerDetaiList.get(hisConsumerDetaiList.size() - 1);
        assertThat(testHisConsumerDetai.getResourceId()).isEqualTo(DEFAULT_RESOURCE_ID);
        assertThat(testHisConsumerDetai.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHisConsumerDetai.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testHisConsumerDetai.getResourceType()).isEqualTo(DEFAULT_RESOURCE_TYPE);
        assertThat(testHisConsumerDetai.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testHisConsumerDetai.getShowTime()).isEqualTo(DEFAULT_SHOW_TIME);
        assertThat(testHisConsumerDetai.getDataPath()).isEqualTo(DEFAULT_DATA_PATH);
        assertThat(testHisConsumerDetai.getCoverPicPath()).isEqualTo(DEFAULT_COVER_PIC_PATH);
        assertThat(testHisConsumerDetai.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testHisConsumerDetai.getDataMd()).isEqualTo(DEFAULT_DATA_MD);
        assertThat(testHisConsumerDetai.getDataSuffix()).isEqualTo(DEFAULT_DATA_SUFFIX);
        assertThat(testHisConsumerDetai.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testHisConsumerDetai.getPlanTime()).isEqualTo(DEFAULT_PLAN_TIME);
        assertThat(testHisConsumerDetai.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testHisConsumerDetai.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHisConsumerDetai.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testHisConsumerDetai.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createHisConsumerDetaiWithExistingId() throws Exception {
        // Create the HisConsumerDetai with an existing ID
        hisConsumerDetai.setId(1L);

        int databaseSizeBeforeCreate = hisConsumerDetaiRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHisConsumerDetaiMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHisConsumerDetais() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        // Get all the hisConsumerDetaiList
        restHisConsumerDetaiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hisConsumerDetai.getId().intValue())))
            .andExpect(jsonPath("$.[*].resourceId").value(hasItem(DEFAULT_RESOURCE_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].resourceType").value(hasItem(DEFAULT_RESOURCE_TYPE)))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].showTime").value(hasItem(DEFAULT_SHOW_TIME)))
            .andExpect(jsonPath("$.[*].dataPath").value(hasItem(DEFAULT_DATA_PATH)))
            .andExpect(jsonPath("$.[*].coverPicPath").value(hasItem(DEFAULT_COVER_PIC_PATH)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE)))
            .andExpect(jsonPath("$.[*].dataMd").value(hasItem(DEFAULT_DATA_MD)))
            .andExpect(jsonPath("$.[*].dataSuffix").value(hasItem(DEFAULT_DATA_SUFFIX)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].planTime").value(hasItem(DEFAULT_PLAN_TIME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getHisConsumerDetai() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        // Get the hisConsumerDetai
        restHisConsumerDetaiMockMvc
            .perform(get(ENTITY_API_URL_ID, hisConsumerDetai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hisConsumerDetai.getId().intValue()))
            .andExpect(jsonPath("$.resourceId").value(DEFAULT_RESOURCE_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.resourceType").value(DEFAULT_RESOURCE_TYPE))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE.toString()))
            .andExpect(jsonPath("$.showTime").value(DEFAULT_SHOW_TIME))
            .andExpect(jsonPath("$.dataPath").value(DEFAULT_DATA_PATH))
            .andExpect(jsonPath("$.coverPicPath").value(DEFAULT_COVER_PIC_PATH))
            .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE))
            .andExpect(jsonPath("$.dataMd").value(DEFAULT_DATA_MD))
            .andExpect(jsonPath("$.dataSuffix").value(DEFAULT_DATA_SUFFIX))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.planTime").value(DEFAULT_PLAN_TIME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHisConsumerDetai() throws Exception {
        // Get the hisConsumerDetai
        restHisConsumerDetaiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHisConsumerDetai() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();

        // Update the hisConsumerDetai
        HisConsumerDetai updatedHisConsumerDetai = hisConsumerDetaiRepository.findById(hisConsumerDetai.getId()).get();
        // Disconnect from session so that the updates on updatedHisConsumerDetai are not directly saved in db
        em.detach(updatedHisConsumerDetai);
        updatedHisConsumerDetai
            .resourceId(UPDATED_RESOURCE_ID)
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restHisConsumerDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHisConsumerDetai.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHisConsumerDetai))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerDetai testHisConsumerDetai = hisConsumerDetaiList.get(hisConsumerDetaiList.size() - 1);
        assertThat(testHisConsumerDetai.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
        assertThat(testHisConsumerDetai.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHisConsumerDetai.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testHisConsumerDetai.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testHisConsumerDetai.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testHisConsumerDetai.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testHisConsumerDetai.getDataPath()).isEqualTo(UPDATED_DATA_PATH);
        assertThat(testHisConsumerDetai.getCoverPicPath()).isEqualTo(UPDATED_COVER_PIC_PATH);
        assertThat(testHisConsumerDetai.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testHisConsumerDetai.getDataMd()).isEqualTo(UPDATED_DATA_MD);
        assertThat(testHisConsumerDetai.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testHisConsumerDetai.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testHisConsumerDetai.getPlanTime()).isEqualTo(UPDATED_PLAN_TIME);
        assertThat(testHisConsumerDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerDetai.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hisConsumerDetai.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHisConsumerDetaiWithPatch() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();

        // Update the hisConsumerDetai using partial update
        HisConsumerDetai partialUpdatedHisConsumerDetai = new HisConsumerDetai();
        partialUpdatedHisConsumerDetai.setId(hisConsumerDetai.getId());

        partialUpdatedHisConsumerDetai
            .resourceId(UPDATED_RESOURCE_ID)
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restHisConsumerDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHisConsumerDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHisConsumerDetai))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerDetai testHisConsumerDetai = hisConsumerDetaiList.get(hisConsumerDetaiList.size() - 1);
        assertThat(testHisConsumerDetai.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
        assertThat(testHisConsumerDetai.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHisConsumerDetai.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testHisConsumerDetai.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testHisConsumerDetai.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testHisConsumerDetai.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testHisConsumerDetai.getDataPath()).isEqualTo(UPDATED_DATA_PATH);
        assertThat(testHisConsumerDetai.getCoverPicPath()).isEqualTo(UPDATED_COVER_PIC_PATH);
        assertThat(testHisConsumerDetai.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testHisConsumerDetai.getDataMd()).isEqualTo(UPDATED_DATA_MD);
        assertThat(testHisConsumerDetai.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testHisConsumerDetai.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testHisConsumerDetai.getPlanTime()).isEqualTo(UPDATED_PLAN_TIME);
        assertThat(testHisConsumerDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerDetai.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateHisConsumerDetaiWithPatch() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();

        // Update the hisConsumerDetai using partial update
        HisConsumerDetai partialUpdatedHisConsumerDetai = new HisConsumerDetai();
        partialUpdatedHisConsumerDetai.setId(hisConsumerDetai.getId());

        partialUpdatedHisConsumerDetai
            .resourceId(UPDATED_RESOURCE_ID)
            .name(UPDATED_NAME)
            .content(UPDATED_CONTENT)
            .resourceType(UPDATED_RESOURCE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .showTime(UPDATED_SHOW_TIME)
            .dataPath(UPDATED_DATA_PATH)
            .coverPicPath(UPDATED_COVER_PIC_PATH)
            .dataSize(UPDATED_DATA_SIZE)
            .dataMd(UPDATED_DATA_MD)
            .dataSuffix(UPDATED_DATA_SUFFIX)
            .remark(UPDATED_REMARK)
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restHisConsumerDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHisConsumerDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHisConsumerDetai))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerDetai testHisConsumerDetai = hisConsumerDetaiList.get(hisConsumerDetaiList.size() - 1);
        assertThat(testHisConsumerDetai.getResourceId()).isEqualTo(UPDATED_RESOURCE_ID);
        assertThat(testHisConsumerDetai.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHisConsumerDetai.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testHisConsumerDetai.getResourceType()).isEqualTo(UPDATED_RESOURCE_TYPE);
        assertThat(testHisConsumerDetai.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testHisConsumerDetai.getShowTime()).isEqualTo(UPDATED_SHOW_TIME);
        assertThat(testHisConsumerDetai.getDataPath()).isEqualTo(UPDATED_DATA_PATH);
        assertThat(testHisConsumerDetai.getCoverPicPath()).isEqualTo(UPDATED_COVER_PIC_PATH);
        assertThat(testHisConsumerDetai.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testHisConsumerDetai.getDataMd()).isEqualTo(UPDATED_DATA_MD);
        assertThat(testHisConsumerDetai.getDataSuffix()).isEqualTo(UPDATED_DATA_SUFFIX);
        assertThat(testHisConsumerDetai.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testHisConsumerDetai.getPlanTime()).isEqualTo(UPDATED_PLAN_TIME);
        assertThat(testHisConsumerDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerDetai.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hisConsumerDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHisConsumerDetai() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerDetaiRepository.findAll().size();
        hisConsumerDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerDetai))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HisConsumerDetai in the database
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHisConsumerDetai() throws Exception {
        // Initialize the database
        hisConsumerDetaiRepository.saveAndFlush(hisConsumerDetai);

        int databaseSizeBeforeDelete = hisConsumerDetaiRepository.findAll().size();

        // Delete the hisConsumerDetai
        restHisConsumerDetaiMockMvc
            .perform(delete(ENTITY_API_URL_ID, hisConsumerDetai.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HisConsumerDetai> hisConsumerDetaiList = hisConsumerDetaiRepository.findAll();
        assertThat(hisConsumerDetaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
