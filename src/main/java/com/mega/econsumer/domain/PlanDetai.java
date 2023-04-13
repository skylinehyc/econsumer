package com.mega.econsumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

/**
 * 消费计划明细\n场景1：消费者消费资源的计划明细\n@author skyline
 */
@Schema(description = "消费计划明细\n场景1：消费者消费资源的计划明细\n@author skyline")
@Entity
@Table(name = "ecs_plan_detai")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PlanDetai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 计划每日开始时间-结束时间 多个竖杠|分隔\n例如：10:00-11:00|11:00-12:00|23:00-24:00
     */
    @Schema(description = "计划每日开始时间-结束时间 多个竖杠|分隔\n例如：10:00-11:00|11:00-12:00|23:00-24:00")
    @Column(name = "plan_time")
    private String planTime;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建日期
     */
    @Schema(description = "创建日期")
    @Column(name = "created_date")
    private Instant createdDate;

    /**
     * 修改者
     */
    @Schema(description = "修改者")
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    /**
     * 修改日期
     */
    @Schema(description = "修改日期")
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "planDetais", "resourceMetadata" }, allowSetters = true)
    private Consumer consumer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "planDetais" }, allowSetters = true)
    private PlanHeader planHeader;

    @ManyToOne
    @JsonIgnoreProperties(value = { "planDetais", "consumer" }, allowSetters = true)
    private ResourceMetadata resourceMetadata;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PlanDetai id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanTime() {
        return this.planTime;
    }

    public PlanDetai planTime(String planTime) {
        this.setPlanTime(planTime);
        return this;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public PlanDetai createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public PlanDetai createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public PlanDetai lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public PlanDetai lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Consumer getConsumer() {
        return this.consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public PlanDetai consumer(Consumer consumer) {
        this.setConsumer(consumer);
        return this;
    }

    public PlanHeader getPlanHeader() {
        return this.planHeader;
    }

    public void setPlanHeader(PlanHeader planHeader) {
        this.planHeader = planHeader;
    }

    public PlanDetai planHeader(PlanHeader planHeader) {
        this.setPlanHeader(planHeader);
        return this;
    }

    public ResourceMetadata getResourceMetadata() {
        return this.resourceMetadata;
    }

    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }

    public PlanDetai resourceMetadata(ResourceMetadata resourceMetadata) {
        this.setResourceMetadata(resourceMetadata);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanDetai)) {
            return false;
        }
        return id != null && id.equals(((PlanDetai) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlanDetai{" +
            "id=" + getId() +
            ", planTime='" + getPlanTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
