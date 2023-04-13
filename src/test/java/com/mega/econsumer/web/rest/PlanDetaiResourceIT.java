package com.mega.econsumer.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.econsumer.IntegrationTest;
import com.mega.econsumer.domain.PlanDetai;
import com.mega.econsumer.repository.PlanDetaiRepository;
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
 * Integration tests for the {@link PlanDetaiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanDetaiResourceIT {

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

    private static final String ENTITY_API_URL = "/api/plan-detais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PlanDetaiRepository planDetaiRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanDetaiMockMvc;

    private PlanDetai planDetai;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanDetai createEntity(EntityManager em) {
        PlanDetai planDetai = new PlanDetai()
            .planTime(DEFAULT_PLAN_TIME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return planDetai;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanDetai createUpdatedEntity(EntityManager em) {
        PlanDetai planDetai = new PlanDetai()
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return planDetai;
    }

    @BeforeEach
    public void initTest() {
        planDetai = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanDetai() throws Exception {
        int databaseSizeBeforeCreate = planDetaiRepository.findAll().size();
        // Create the PlanDetai
        restPlanDetaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planDetai)))
            .andExpect(status().isCreated());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeCreate + 1);
        PlanDetai testPlanDetai = planDetaiList.get(planDetaiList.size() - 1);
        assertThat(testPlanDetai.getPlanTime()).isEqualTo(DEFAULT_PLAN_TIME);
        assertThat(testPlanDetai.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPlanDetai.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPlanDetai.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testPlanDetai.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createPlanDetaiWithExistingId() throws Exception {
        // Create the PlanDetai with an existing ID
        planDetai.setId(1L);

        int databaseSizeBeforeCreate = planDetaiRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanDetaiMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planDetai)))
            .andExpect(status().isBadRequest());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanDetais() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        // Get all the planDetaiList
        restPlanDetaiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planDetai.getId().intValue())))
            .andExpect(jsonPath("$.[*].planTime").value(hasItem(DEFAULT_PLAN_TIME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getPlanDetai() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        // Get the planDetai
        restPlanDetaiMockMvc
            .perform(get(ENTITY_API_URL_ID, planDetai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planDetai.getId().intValue()))
            .andExpect(jsonPath("$.planTime").value(DEFAULT_PLAN_TIME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPlanDetai() throws Exception {
        // Get the planDetai
        restPlanDetaiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanDetai() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();

        // Update the planDetai
        PlanDetai updatedPlanDetai = planDetaiRepository.findById(planDetai.getId()).get();
        // Disconnect from session so that the updates on updatedPlanDetai are not directly saved in db
        em.detach(updatedPlanDetai);
        updatedPlanDetai
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanDetai.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPlanDetai))
            )
            .andExpect(status().isOk());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
        PlanDetai testPlanDetai = planDetaiList.get(planDetaiList.size() - 1);
        assertThat(testPlanDetai.getPlanTime()).isEqualTo(UPDATED_PLAN_TIME);
        assertThat(testPlanDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanDetai.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPlanDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPlanDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planDetai.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planDetai)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanDetaiWithPatch() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();

        // Update the planDetai using partial update
        PlanDetai partialUpdatedPlanDetai = new PlanDetai();
        partialUpdatedPlanDetai.setId(planDetai.getId());

        partialUpdatedPlanDetai
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanDetai))
            )
            .andExpect(status().isOk());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
        PlanDetai testPlanDetai = planDetaiList.get(planDetaiList.size() - 1);
        assertThat(testPlanDetai.getPlanTime()).isEqualTo(DEFAULT_PLAN_TIME);
        assertThat(testPlanDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanDetai.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPlanDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPlanDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdatePlanDetaiWithPatch() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();

        // Update the planDetai using partial update
        PlanDetai partialUpdatedPlanDetai = new PlanDetai();
        partialUpdatedPlanDetai.setId(planDetai.getId());

        partialUpdatedPlanDetai
            .planTime(UPDATED_PLAN_TIME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPlanDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanDetai))
            )
            .andExpect(status().isOk());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
        PlanDetai testPlanDetai = planDetaiList.get(planDetaiList.size() - 1);
        assertThat(testPlanDetai.getPlanTime()).isEqualTo(UPDATED_PLAN_TIME);
        assertThat(testPlanDetai.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPlanDetai.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPlanDetai.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPlanDetai.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planDetai.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planDetai))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanDetai() throws Exception {
        int databaseSizeBeforeUpdate = planDetaiRepository.findAll().size();
        planDetai.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanDetaiMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(planDetai))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanDetai in the database
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanDetai() throws Exception {
        // Initialize the database
        planDetaiRepository.saveAndFlush(planDetai);

        int databaseSizeBeforeDelete = planDetaiRepository.findAll().size();

        // Delete the planDetai
        restPlanDetaiMockMvc
            .perform(delete(ENTITY_API_URL_ID, planDetai.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanDetai> planDetaiList = planDetaiRepository.findAll();
        assertThat(planDetaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
