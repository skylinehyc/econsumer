package com.mega.econsumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.econsumer.domain.enumeration.PlanState;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * 消费计划头\n场景1：投屏计划\n@author skyline
 */
@Schema(description = "消费计划头\n场景1：投屏计划\n@author skyline")
@Entity
@Table(name = "ecs_plan_header")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PlanHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 计划编码
     */
    @Schema(description = "计划编码", required = true)
    @NotNull
    @Size(max = 255)
    @Column(name = "plan_code", length = 255, nullable = false, unique = true)
    private String planCode;

    /**
     * 计划名称
     */
    @Schema(description = "计划名称")
    @Size(max = 255)
    @Column(name = "plan_name", length = 255)
    private String planName;

    /**
     * 计划状态
     */
    @Schema(description = "计划状态")
    @Enumerated(EnumType.STRING)
    @Column(name = "plan_state")
    private PlanState planState;

    /**
     * 计划开始时间\nYYYY-MM-DD 00:00:00
     */
    @Schema(description = "计划开始时间\nYYYY-MM-DD 00:00:00", required = true)
    @NotNull
    @Column(name = "plan_start_time", nullable = false)
    private Instant planStartTime;

    /**
     * 计划结束时间\nYYYY-MM-DD 23:59:59
     */
    @Schema(description = "计划结束时间\nYYYY-MM-DD 23:59:59", required = true)
    @NotNull
    @Column(name = "plan_end_time", nullable = false)
    private Instant planEndTime;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 1000)
    @Column(name = "remark", length = 1000)
    private String remark;

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

    @OneToMany(mappedBy = "planHeader")
    @JsonIgnoreProperties(value = { "consumer", "planHeader", "resourceMetadata" }, allowSetters = true)
    private Set<PlanDetai> planDetais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PlanHeader id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanCode() {
        return this.planCode;
    }

    public PlanHeader planCode(String planCode) {
        this.setPlanCode(planCode);
        return this;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanName() {
        return this.planName;
    }

    public PlanHeader planName(String planName) {
        this.setPlanName(planName);
        return this;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public PlanState getPlanState() {
        return this.planState;
    }

    public PlanHeader planState(PlanState planState) {
        this.setPlanState(planState);
        return this;
    }

    public void setPlanState(PlanState planState) {
        this.planState = planState;
    }

    public Instant getPlanStartTime() {
        return this.planStartTime;
    }

    public PlanHeader planStartTime(Instant planStartTime) {
        this.setPlanStartTime(planStartTime);
        return this;
    }

    public void setPlanStartTime(Instant planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Instant getPlanEndTime() {
        return this.planEndTime;
    }

    public PlanHeader planEndTime(Instant planEndTime) {
        this.setPlanEndTime(planEndTime);
        return this;
    }

    public void setPlanEndTime(Instant planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public PlanHeader remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public PlanHeader createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public PlanHeader createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public PlanHeader lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public PlanHeader lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<PlanDetai> getPlanDetais() {
        return this.planDetais;
    }

    public void setPlanDetais(Set<PlanDetai> planDetais) {
        if (this.planDetais != null) {
            this.planDetais.forEach(i -> i.setPlanHeader(null));
        }
        if (planDetais != null) {
            planDetais.forEach(i -> i.setPlanHeader(this));
        }
        this.planDetais = planDetais;
    }

    public PlanHeader planDetais(Set<PlanDetai> planDetais) {
        this.setPlanDetais(planDetais);
        return this;
    }

    public PlanHeader addPlanDetai(PlanDetai planDetai) {
        this.planDetais.add(planDetai);
        planDetai.setPlanHeader(this);
        return this;
    }

    public PlanHeader removePlanDetai(PlanDetai planDetai) {
        this.planDetais.remove(planDetai);
        planDetai.setPlanHeader(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanHeader)) {
            return false;
        }
        return id != null && id.equals(((PlanHeader) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlanHeader{" +
            "id=" + getId() +
            ", planCode='" + getPlanCode() + "'" +
            ", planName='" + getPlanName() + "'" +
            ", planState='" + getPlanState() + "'" +
            ", planStartTime='" + getPlanStartTime() + "'" +
            ", planEndTime='" + getPlanEndTime() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
