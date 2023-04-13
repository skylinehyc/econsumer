package com.mega.econsumer.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.econsumer.IntegrationTest;
import com.mega.econsumer.domain.PlanHeader;
import com.mega.econsumer.domain.enumeration.PlanState;
import com.mega.econsumer.repository.PlanHeaderRepository;
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
 * Integration tests for the {@link PlanHeaderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanHeaderResourceIT {

    private static final String DEFAULT_PLAN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_NAME = "BBBBBBBBBB";

    private static final PlanState DEFAULT_PLAN_STATE = PlanState.OPEN;
    private static final PlanState UPDATED_PLAN_STATE = PlanState.CLOSE;

    private static final Instant DEFAULT_PLAN_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PLAN_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PLAN_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PLAN_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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

    private static final String ENTITY_API_URL = "/api/plan-headers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PlanHeaderRepository planHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanHeaderMockMvc;

    private PlanHeader planHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanHeader createEntity(EntityManager em) {
        PlanHeader planHeader = new PlanHeader()
            .planCode(DEFAULT_PLAN_CODE)
            .planName(DEFAULT_PLAN_NAME)
            .planState(DEFAULT_PLAN_STATE)
            .planStartTime(DEFAULT_PLAN_START_TIME)
            .planEndTime(DEFAULT_PLAN_END_TIME)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return planHeader;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanHeader createUpdatedEntity(EntityManager em) {
        PlanHeader planHeader = new PlanHeader()
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planState(UPDATED_PLAN_STATE)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return planHeader;
    }

    @BeforeEach
    public void initTest() {
        planHeader = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanHeader() throws Exception {
        int databaseSizeBeforeCreate = planHeaderRepository.findAll().size();
        // Create the PlanHeader
        restPlanHeaderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isCreated());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        PlanHeader testPlanHeader = planHeaderList.get(planHeaderList.size() - 1);
        assertThat(testPlanHeader.getPlanCode()).isEqualTo(DEFAULT_PLAN_CODE);
        assertThat(testPlanHeader.getPlanName()).isEqualTo(DEFAULT_PLAN_NAME);
        assertThat(testPlanHeader.getPlanState()).isEqualTo(DEFAULT_PLAN_STATE);
        assertThat(testPlanHeader.getPlanStartTime()).isEqualTo(DEFAULT_PLAN_START_TIME);
        assertThat(testPlanHeader.getPlanEndTime()).isEqualTo(DEFAULT_PLAN_END_TIME);
        assertThat(testPlanHeader.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testPlanHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPlanHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPlanHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testPlanHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createPlanHeaderWithExistingId() throws Exception {
        // Create the PlanHeader with an existing ID
        planHeader.setId(1L);

        int databaseSizeBeforeCreate = planHeaderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanHeaderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPlanCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planHeaderRepository.findAll().size();
        // set the field null
        planHeader.setPlanCode(null);

        // Create the PlanHeader, which fails.

        restPlanHeaderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isBadRequest());

        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planHeaderRepository.findAll().size();
        // set the field null
        planHeader.setPlanStartTime(null);

        // Create the PlanHeader, which fails.

        restPlanHeaderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isBadRequest());

        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = planHeaderRepository.findAll().size();
        // set the field null
        planHeader.setPlanEndTime(null);

        // Create the PlanHeader, which fails.

        restPlanHeaderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isBadRequest());

        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPlanHeaders() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        // Get all the planHeaderList
        restPlanHeaderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].planCode").value(hasItem(DEFAULT_PLAN_CODE)))
            .andExpect(jsonPath("$.[*].planName").value(hasItem(DEFAULT_PLAN_NAME)))
            .andExpect(jsonPath("$.[*].planState").value(hasItem(DEFAULT_PLAN_STATE.toString())))
            .andExpect(jsonPath("$.[*].planStartTime").value(hasItem(DEFAULT_PLAN_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].planEndTime").value(hasItem(DEFAULT_PLAN_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getPlanHeader() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        // Get the planHeader
        restPlanHeaderMockMvc
            .perform(get(ENTITY_API_URL_ID, planHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planHeader.getId().intValue()))
            .andExpect(jsonPath("$.planCode").value(DEFAULT_PLAN_CODE))
            .andExpect(jsonPath("$.planName").value(DEFAULT_PLAN_NAME))
            .andExpect(jsonPath("$.planState").value(DEFAULT_PLAN_STATE.toString()))
            .andExpect(jsonPath("$.planStartTime").value(DEFAULT_PLAN_START_TIME.toString()))
            .andExpect(jsonPath("$.planEndTime").value(DEFAULT_PLAN_END_TIME.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPlanHeader() throws Exception {
        // Get the planHeader
        restPlanHeaderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanHeader() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();

        // Update the planHeader
        PlanHeader updatedPlanHeader = planHeaderRepository.findById(planHeader.getId()).get();
        // Disconnect from session so that the updates on updatedPlanHeader are not directly saved in db
        em.detach(updatedPlanHeader);
        updatedPlanHeader
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planState(UPDATED_PLAN_STATE)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPlanHeader))
            )
            .andExpect(status().isOk());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
        PlanHeader testPlanHeader = planHeaderList.get(planHeaderList.size() - 1);
        assertThat(testPlanHeader.getPlanCode()).isEqualTo(UPDATED_PLAN_CODE);
        assertThat(testPlanHeader.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testPlanHeader.getPlanState()).isEqualTo(UPDATED_PLAN_STATE);
        assertThat(testPlanHeader.getPlanStartTime()).isEqualTo(UPDATED_PLAN_START_TIME);
        assertThat(testPlanHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testPlanHeader.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testPlanHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPlanHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPlanHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planHeader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planHeader)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanHeaderWithPatch() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();

        // Update the planHeader using partial update
        PlanHeader partialUpdatedPlanHeader = new PlanHeader();
        partialUpdatedPlanHeader.setId(planHeader.getId());

        partialUpdatedPlanHeader
            .planName(UPDATED_PLAN_NAME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanHeader))
            )
            .andExpect(status().isOk());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
        PlanHeader testPlanHeader = planHeaderList.get(planHeaderList.size() - 1);
        assertThat(testPlanHeader.getPlanCode()).isEqualTo(DEFAULT_PLAN_CODE);
        assertThat(testPlanHeader.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testPlanHeader.getPlanState()).isEqualTo(DEFAULT_PLAN_STATE);
        assertThat(testPlanHeader.getPlanStartTime()).isEqualTo(DEFAULT_PLAN_START_TIME);
        assertThat(testPlanHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testPlanHeader.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testPlanHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPlanHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testPlanHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdatePlanHeaderWithPatch() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();

        // Update the planHeader using partial update
        PlanHeader partialUpdatedPlanHeader = new PlanHeader();
        partialUpdatedPlanHeader.setId(planHeader.getId());

        partialUpdatedPlanHeader
            .planCode(UPDATED_PLAN_CODE)
            .planName(UPDATED_PLAN_NAME)
            .planState(UPDATED_PLAN_STATE)
            .planStartTime(UPDATED_PLAN_START_TIME)
            .planEndTime(UPDATED_PLAN_END_TIME)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanHeader))
            )
            .andExpect(status().isOk());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
        PlanHeader testPlanHeader = planHeaderList.get(planHeaderList.size() - 1);
        assertThat(testPlanHeader.getPlanCode()).isEqualTo(UPDATED_PLAN_CODE);
        assertThat(testPlanHeader.getPlanName()).isEqualTo(UPDATED_PLAN_NAME);
        assertThat(testPlanHeader.getPlanState()).isEqualTo(UPDATED_PLAN_STATE);
        assertThat(testPlanHeader.getPlanStartTime()).isEqualTo(UPDATED_PLAN_START_TIME);
        assertThat(testPlanHeader.getPlanEndTime()).isEqualTo(UPDATED_PLAN_END_TIME);
        assertThat(testPlanHeader.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testPlanHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPlanHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPlanHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planHeader.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanHeader() throws Exception {
        int databaseSizeBeforeUpdate = planHeaderRepository.findAll().size();
        planHeader.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanHeaderMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(planHeader))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanHeader in the database
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanHeader() throws Exception {
        // Initialize the database
        planHeaderRepository.saveAndFlush(planHeader);

        int databaseSizeBeforeDelete = planHeaderRepository.findAll().size();

        // Delete the planHeader
        restPlanHeaderMockMvc
            .perform(delete(ENTITY_API_URL_ID, planHeader.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanHeader> planHeaderList = planHeaderRepository.findAll();
        assertThat(planHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
