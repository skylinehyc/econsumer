package com.mega.econsumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.econsumer.domain.enumeration.ConsumerBusinessType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * 消费历史头表\n场景1：消费计划生效后 记录生效的计划信息到历史表\n历史表进行冗余设计 将消费者和计划的对象属性都设置进来\n这样消费者和计划被删除也没关系\n@author skyline
 */
@Schema(
    description = "消费历史头表\n场景1：消费计划生效后 记录生效的计划信息到历史表\n历史表进行冗余设计 将消费者和计划的对象属性都设置进来\n这样消费者和计划被删除也没关系\n@author skyline"
)
@Entity
@Table(name = "ecs_his_consumer_header")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HisConsumerHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 消费者业务id\n
     */
    @Schema(description = "消费者业务id\n", required = true)
    @NotNull
    @Size(max = 255)
    @Column(name = "business_id", length = 255, nullable = false)
    private String businessId;

    /**
     * 业务id类型：MAC
     */
    @Schema(description = "业务id类型：MAC")
    @Enumerated(EnumType.STRING)
    @Column(name = "business_type")
    private ConsumerBusinessType businessType;

    /**
     * 工厂id
     */
    @Schema(description = "工厂id")
    @Column(name = "fac_id")
    private String facId;

    /**
     * 区域id
     */
    @Schema(description = "区域id")
    @Column(name = "area_id")
    private String areaId;

    /**
     * 计划编码
     */
    @Schema(description = "计划编码")
    @Size(max = 255)
    @Column(name = "plan_code", length = 255)
    private String planCode;

    /**
     * 计划名称
     */
    @Schema(description = "计划名称")
    @Size(max = 255)
    @Column(name = "plan_name", length = 255)
    private String planName;

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

    @OneToMany(mappedBy = "hisConsumerHeader")
    @JsonIgnoreProperties(value = { "hisConsumerHeader" }, allowSetters = true)
    private Set<HisConsumerDetai> hisConsumerDetais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HisConsumerHeader id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public HisConsumerHeader businessId(String businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public ConsumerBusinessType getBusinessType() {
        return this.businessType;
    }

    public HisConsumerHeader businessType(ConsumerBusinessType businessType) {
        this.setBusinessType(businessType);
        return this;
    }

    public void setBusinessType(ConsumerBusinessType businessType) {
        this.businessType = businessType;
    }

    public String getFacId() {
        return this.facId;
    }

    public HisConsumerHeader facId(String facId) {
        this.setFacId(facId);
        return this;
    }

    public void setFacId(String facId) {
        this.facId = facId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public HisConsumerHeader areaId(String areaId) {
        this.setAreaId(areaId);
        return this;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPlanCode() {
        return this.planCode;
    }

    public HisConsumerHeader planCode(String planCode) {
        this.setPlanCode(planCode);
        return this;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanName() {
        return this.planName;
    }

    public HisConsumerHeader planName(String planName) {
        this.setPlanName(planName);
        return this;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Instant getPlanStartTime() {
        return this.planStartTime;
    }

    public HisConsumerHeader planStartTime(Instant planStartTime) {
        this.setPlanStartTime(planStartTime);
        return this;
    }

    public void setPlanStartTime(Instant planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Instant getPlanEndTime() {
        return this.planEndTime;
    }

    public HisConsumerHeader planEndTime(Instant planEndTime) {
        this.setPlanEndTime(planEndTime);
        return this;
    }

    public void setPlanEndTime(Instant planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public HisConsumerHeader createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public HisConsumerHeader createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public HisConsumerHeader lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public HisConsumerHeader lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<HisConsumerDetai> getHisConsumerDetais() {
        return this.hisConsumerDetais;
    }

    public void setHisConsumerDetais(Set<HisConsumerDetai> hisConsumerDetais) {
        if (this.hisConsumerDetais != null) {
            this.hisConsumerDetais.forEach(i -> i.setHisConsumerHeader(null));
        }
        if (hisConsumerDetais != null) {
            hisConsumerDetais.forEach(i -> i.setHisConsumerHeader(this));
        }
        this.hisConsumerDetais = hisConsumerDetais;
    }

    public HisConsumerHeader hisConsumerDetais(Set<HisConsumerDetai> hisConsumerDetais) {
        this.setHisConsumerDetais(hisConsumerDetais);
        return this;
    }

    public HisConsumerHeader addHisConsumerDetai(HisConsumerDetai hisConsumerDetai) {
        this.hisConsumerDetais.add(hisConsumerDetai);
        hisConsumerDetai.setHisConsumerHeader(this);
        return this;
    }

    public HisConsumerHeader removeHisConsumerDetai(HisConsumerDetai hisConsumerDetai) {
        this.hisConsumerDetais.remove(hisConsumerDetai);
        hisConsumerDetai.setHisConsumerHeader(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HisConsumerHeader)) {
            return false;
        }
        return id != null && id.equals(((HisConsumerHeader) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HisConsumerHeader{" +
            "id=" + getId() +
            ", businessId='" + getBusinessId() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", facId='" + getFacId() + "'" +
            ", areaId='" + getAreaId() + "'" +
            ", planCode='" + getPlanCode() + "'" +
            ", planName='" + getPlanName() + "'" +
            ", planStartTime='" + getPlanStartTime() + "'" +
            ", planEndTime='" + getPlanEndTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
