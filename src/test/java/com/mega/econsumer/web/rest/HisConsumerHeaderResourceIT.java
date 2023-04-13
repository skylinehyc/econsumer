package com.mega.econsumer.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.econsumer.IntegrationTest;
import com.mega.econsumer.domain.HisConsumerHeader;
import com.mega.econsumer.domain.enumeration.ConsumerBusinessType;
import com.mega.econsumer.repository.HisConsumerHeaderRepository;
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
 * Integration tests for the {@link HisConsumerHeaderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HisConsumerHeaderResourceIT {

    private static final String DEFAULT_BUSINESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_ID = "BBBBBBBBBB";

    private static final ConsumerBusinessType DEFAULT_BUSINESS_TYPE = ConsumerBusinessType.MAC;
    private static final ConsumerBusinessType UPDATED_BUSINESS_TYPE = ConsumerBusinessType.MAC;

    private static final String DEFAULT_FAC_ID = "AAAAAAAAAA";
    private static final String UPDATED_FAC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_ID = "AAAAAAAAAA";
    private static final String UPDATED_AREA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_PLAN_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PLAN_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PLAN_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PLAN_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/his-consumer-headers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HisConsumerHeaderRepository hisConsumerHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHisConsumerHeaderMockMvc;

    private HisConsumerHeader hisConsumerHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HisConsumerHeader createEntity(EntityManager em) {
        HisConsumerHeader hisConsumerHeader = new HisConsumerHeader()
            .businessId(DEFAULT_BUSINESS_ID)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .facId(DEFAULT_FAC_ID)
            .areaId(DEFAULT_AREA_ID)
            .planCode(DEFAULT_PLAN_CODE)
            .planName(DEFAULT_PLAN_NAME)
            .planStartTime(DEFAULT_PLAN_START_TIME)
            .planEndTime(DEFAULT_PLAN_END_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return hisConsumerHeader;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HisConsumerHeader createUpdatedEntity(EntityManager em) {
        HisConsumerHeader hisConsumerHeader = new HisConsumerHeader()
            .businessId(UPDATED_BUSINESS_ID)
            .businessType(UPDATED_BUSINESS_TYPE)
            .facId(UPDATED_FAC_ID)
            .areaId(UPDATED_AREA_ID)
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return hisConsumerHeader;
    }

    @BeforeEach
    public void initTest() {
        hisConsumerHeader = createEntity(em);
    }

    @Test
    @Transactional
    void createHisConsumerHeader() throws Exception {
        int databaseSizeBeforeCreate = hisConsumerHeaderRepository.findAll().size();
        // Create the HisConsumerHeader
        restHisConsumerHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isCreated());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        HisConsumerHeader testHisConsumerHeader = hisConsumerHeaderList.get(hisConsumerHeaderList.size() - 1);
        assertThat(testHisConsumerHeader.getBusinessId()).isEqualTo(DEFAULT_BUSINESS_ID);
        assertThat(testHisConsumerHeader.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testHisConsumerHeader.getFacId()).isEqualTo(DEFAULT_FAC_ID);
        assertThat(testHisConsumerHeader.getAreaId()).isEqualTo(DEFAULT_AREA_ID);
        assertThat(testHisConsumerHeader.getPlanCode()).isEqualTo(DEFAULT_PLAN_CODE);
        assertThat(testHisConsumerHeader.getPlanName()).isEqualTo(DEFAULT_PLAN_NAME);
        assertThat(testHisConsumerHeader.getPlanStartTime()).isEqualTo(DEFAULT_PLAN_START_TIME);
        assertThat(testHisConsumerHeader.getPlanEndTime()).isEqualTo(DEFAULT_PLAN_END_TIME);
        assertThat(testHisConsumerHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testHisConsumerHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHisConsumerHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testHisConsumerHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createHisConsumerHeaderWithExistingId() throws Exception {
        // Create the HisConsumerHeader with an existing ID
        hisConsumerHeader.setId(1L);

        int databaseSizeBeforeCreate = hisConsumerHeaderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHisConsumerHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBusinessIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = hisConsumerHeaderRepository.findAll().size();
        // set the field null
        hisConsumerHeader.setBusinessId(null);

        // Create the HisConsumerHeader, which fails.

        restHisConsumerHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hisConsumerHeaderRepository.findAll().size();
        // set the field null
        hisConsumerHeader.setPlanStartTime(null);

        // Create the HisConsumerHeader, which fails.

        restHisConsumerHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hisConsumerHeaderRepository.findAll().size();
        // set the field null
        hisConsumerHeader.setPlanEndTime(null);

        // Create the HisConsumerHeader, which fails.

        restHisConsumerHeaderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHisConsumerHeaders() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        // Get all the hisConsumerHeaderList
        restHisConsumerHeaderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hisConsumerHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].businessId").value(hasItem(DEFAULT_BUSINESS_ID)))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].facId").value(hasItem(DEFAULT_FAC_ID)))
            .andExpect(jsonPath("$.[*].areaId").value(hasItem(DEFAULT_AREA_ID)))
            .andExpect(jsonPath("$.[*].planCode").value(hasItem(DEFAULT_PLAN_CODE)))
            .andExpect(jsonPath("$.[*].planName").value(hasItem(DEFAULT_PLAN_NAME)))
            .andExpect(jsonPath("$.[*].planStartTime").value(hasItem(DEFAULT_PLAN_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].planEndTime").value(hasItem(DEFAULT_PLAN_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getHisConsumerHeader() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        // Get the hisConsumerHeader
        restHisConsumerHeaderMockMvc
            .perform(get(ENTITY_API_URL_ID, hisConsumerHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hisConsumerHeader.getId().intValue()))
            .andExpect(jsonPath("$.businessId").value(DEFAULT_BUSINESS_ID))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE.toString()))
            .andExpect(jsonPath("$.facId").value(DEFAULT_FAC_ID))
            .andExpect(jsonPath("$.areaId").value(DEFAULT_AREA_ID))
            .andExpect(jsonPath("$.planCode").value(DEFAULT_PLAN_CODE))
            .andExpect(jsonPath("$.planName").value(DEFAULT_PLAN_NAME))
            .andExpect(jsonPath("$.planStartTime").value(DEFAULT_PLAN_START_TIME.toString()))
            .andExpect(jsonPath("$.planEndTime").value(DEFAULT_PLAN_END_TIME.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHisConsumerHeader() throws Exception {
        // Get the hisConsumerHeader
        restHisConsumerHeaderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHisConsumerHeader() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();

        // Update the hisConsumerHeader
        HisConsumerHeader updatedHisConsumerHeader = hisConsumerHeaderRepository.findById(hisConsumerHeader.getId()).get();
        // Disconnect from session so that the updates on updatedHisConsumerHeader are not directly saved in db
        em.detach(updatedHisConsumerHeader);
        updatedHisConsumerHeader
            .businessId(UPDATED_BUSINESS_ID)
            .businessType(UPDATED_BUSINESS_TYPE)
            .facId(UPDATED_FAC_ID)
            .areaId(UPDATED_AREA_ID)
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restHisConsumerHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHisConsumerHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHisConsumerHeader))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerHeader testHisConsumerHeader = hisConsumerHeaderList.get(hisConsumerHeaderList.size() - 1);
        assertThat(testHisConsumerHeader.getBusinessId()).isEqualTo(UPDATED_BUSINESS_ID);
        assertThat(testHisConsumerHeader.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testHisConsumerHeader.getFacId()).isEqualTo(UPDATED_FAC_ID);
        assertThat(testHisConsumerHeader.getAreaId()).isEqualTo(UPDATED_AREA_ID);
        assertThat(testHisConsumerHeader.getPlanCode()).isEqualTo(UPDATED_PLAN_CODE);
        assertThat(testHisConsumerHeader.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testHisConsumerHeader.getPlanStartTime()).isEqualTo(UPDATED_PLAN_START_TIME);
        assertThat(testHisConsumerHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testHisConsumerHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hisConsumerHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHisConsumerHeaderWithPatch() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();

        // Update the hisConsumerHeader using partial update
        HisConsumerHeader partialUpdatedHisConsumerHeader = new HisConsumerHeader();
        partialUpdatedHisConsumerHeader.setId(hisConsumerHeader.getId());

        partialUpdatedHisConsumerHeader
            .businessId(UPDATED_BUSINESS_ID)
            .facId(UPDATED_FAC_ID)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restHisConsumerHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHisConsumerHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHisConsumerHeader))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerHeader testHisConsumerHeader = hisConsumerHeaderList.get(hisConsumerHeaderList.size() - 1);
        assertThat(testHisConsumerHeader.getBusinessId()).isEqualTo(UPDATED_BUSINESS_ID);
        assertThat(testHisConsumerHeader.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testHisConsumerHeader.getFacId()).isEqualTo(UPDATED_FAC_ID);
        assertThat(testHisConsumerHeader.getAreaId()).isEqualTo(DEFAULT_AREA_ID);
        assertThat(testHisConsumerHeader.getPlanCode()).isEqualTo(DEFAULT_PLAN_CODE);
        assertThat(testHisConsumerHeader.getPlanName()).isEqualTo(DEFAULT_PLAN_NAME);
        assertThat(testHisConsumerHeader.getPlanStartTime()).isEqualTo(UPDATED_PLAN_START_TIME);
        assertThat(testHisConsumerHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testHisConsumerHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateHisConsumerHeaderWithPatch() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();

        // Update the hisConsumerHeader using partial update
        HisConsumerHeader partialUpdatedHisConsumerHeader = new HisConsumerHeader();
        partialUpdatedHisConsumerHeader.setId(hisConsumerHeader.getId());

        partialUpdatedHisConsumerHeader
            .businessId(UPDATED_BUSINESS_ID)
            .businessType(UPDATED_BUSINESS_TYPE)
            .facId(UPDATED_FAC_ID)
            .areaId(UPDATED_AREA_ID)
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restHisConsumerHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHisConsumerHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHisConsumerHeader))
            )
            .andExpect(status().isOk());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
        HisConsumerHeader testHisConsumerHeader = hisConsumerHeaderList.get(hisConsumerHeaderList.size() - 1);
        assertThat(testHisConsumerHeader.getBusinessId()).isEqualTo(UPDATED_BUSINESS_ID);
        assertThat(testHisConsumerHeader.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testHisConsumerHeader.getFacId()).isEqualTo(UPDATED_FAC_ID);
        assertThat(testHisConsumerHeader.getAreaId()).isEqualTo(UPDATED_AREA_ID);
        assertThat(testHisConsumerHeader.getPlanCode()).isEqualTo(UPDATED_PLAN_CODE);
        assertThat(testHisConsumerHeader.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testHisConsumerHeader.getPlanStartTime()).isEqualTo(UPDATED_PLAN_START_TIME);
        assertThat(testHisConsumerHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testHisConsumerHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHisConsumerHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHisConsumerHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testHisConsumerHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hisConsumerHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHisConsumerHeader() throws Exception {
        int databaseSizeBeforeUpdate = hisConsumerHeaderRepository.findAll().size();
        hisConsumerHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHisConsumerHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hisConsumerHeader))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HisConsumerHeader in the database
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHisConsumerHeader() throws Exception {
        // Initialize the database
        hisConsumerHeaderRepository.saveAndFlush(hisConsumerHeader);

        int databaseSizeBeforeDelete = hisConsumerHeaderRepository.findAll().size();

        // Delete the hisConsumerHeader
        restHisConsumerHeaderMockMvc
            .perform(delete(ENTITY_API_URL_ID, hisConsumerHeader.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HisConsumerHeader> hisConsumerHeaderList = hisConsumerHeaderRepository.findAll();
        assertThat(hisConsumerHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
