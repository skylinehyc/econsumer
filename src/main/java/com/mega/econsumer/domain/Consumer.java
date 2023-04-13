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
 * 资源的消费者\n场景1：使用终端\n@author skyline
 */
@Schema(description = "资源的消费者\n场景1：使用终端\n@author skyline")
@Entity
@Table(name = "ecs_consumer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Consumer implements Serializable {

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
    @Column(name = "business_id", length = 255, nullable = false, unique = true)
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
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 1000)
    @Column(name = "remake", length = 1000)
    private String remake;

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

    @OneToMany(mappedBy = "consumer")
    @JsonIgnoreProperties(value = { "consumer", "planHeader", "resourceMetadata" }, allowSetters = true)
    private Set<PlanDetai> planDetais = new HashSet<>();

    @OneToMany(mappedBy = "consumer")
    @JsonIgnoreProperties(value = { "planDetais", "consumer" }, allowSetters = true)
    private Set<ResourceMetadata> resourceMetadata = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Consumer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public Consumer businessId(String businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public ConsumerBusinessType getBusinessType() {
        return this.businessType;
    }

    public Consumer businessType(ConsumerBusinessType businessType) {
        this.setBusinessType(businessType);
        return this;
    }

    public void setBusinessType(ConsumerBusinessType businessType) {
        this.businessType = businessType;
    }

    public String getFacId() {
        return this.facId;
    }

    public Consumer facId(String facId) {
        this.setFacId(facId);
        return this;
    }

    public void setFacId(String facId) {
        this.facId = facId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public Consumer areaId(String areaId) {
        this.setAreaId(areaId);
        return this;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRemake() {
        return this.remake;
    }

    public Consumer remake(String remake) {
        this.setRemake(remake);
        return this;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Consumer createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public Consumer createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Consumer lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public Consumer lastModifiedDate(Instant lastModifiedDate) {
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
            this.planDetais.forEach(i -> i.setConsumer(null));
        }
        if (planDetais != null) {
            planDetais.forEach(i -> i.setConsumer(this));
        }
        this.planDetais = planDetais;
    }

    public Consumer planDetais(Set<PlanDetai> planDetais) {
        this.setPlanDetais(planDetais);
        return this;
    }

    public Consumer addPlanDetai(PlanDetai planDetai) {
        this.planDetais.add(planDetai);
        planDetai.setConsumer(this);
        return this;
    }

    public Consumer removePlanDetai(PlanDetai planDetai) {
        this.planDetais.remove(planDetai);
        planDetai.setConsumer(null);
        return this;
    }

    public Set<ResourceMetadata> getResourceMetadata() {
        return this.resourceMetadata;
    }

    public void setResourceMetadata(Set<ResourceMetadata> resourceMetadata) {
        if (this.resourceMetadata != null) {
            this.resourceMetadata.forEach(i -> i.setConsumer(null));
        }
        if (resourceMetadata != null) {
            resourceMetadata.forEach(i -> i.setConsumer(this));
        }
        this.resourceMetadata = resourceMetadata;
    }

    public Consumer resourceMetadata(Set<ResourceMetadata> resourceMetadata) {
        this.setResourceMetadata(resourceMetadata);
        return this;
    }

    public Consumer addResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata.add(resourceMetadata);
        resourceMetadata.setConsumer(this);
        return this;
    }

    public Consumer removeResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata.remove(resourceMetadata);
        resourceMetadata.setConsumer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Consumer)) {
            return false;
        }
        return id != null && id.equals(((Consumer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Consumer{" +
            "id=" + getId() +
            ", businessId='" + getBusinessId() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", facId='" + getFacId() + "'" +
            ", areaId='" + getAreaId() + "'" +
            ", remake='" + getRemake() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
