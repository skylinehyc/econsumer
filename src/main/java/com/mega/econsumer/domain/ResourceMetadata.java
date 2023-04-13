package com.mega.econsumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.econsumer.domain.enumeration.ResourceMetadataType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * 资源库(存储图片、视频的元数据信息)\n@author skyline
 */
@Schema(description = "资源库(存储图片、视频的元数据信息)\n@author skyline")
@Entity
@Table(name = "ecs_resource_metadata")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceMetadata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 主题
     */
    @Schema(description = "主题", required = true)
    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;

    /**
     * 内容信息
     */
    @Schema(description = "内容信息")
    @Size(max = 255)
    @Column(name = "content", length = 255)
    private String content;

    /**
     * 资源类型
     */
    @Schema(description = "资源类型")
    @Size(max = 100)
    @Column(name = "resource_type", length = 100)
    private String resourceType;

    /**
     * 元数据类型
     */
    @Schema(description = "元数据类型")
    @Enumerated(EnumType.STRING)
    @Column(name = "data_type")
    private ResourceMetadataType dataType;

    /**
     * 单页展示时间
     */
    @Schema(description = "单页展示时间")
    @Column(name = "show_time")
    private Integer showTime;

    /**
     * 展示开始时间
     */
    @Schema(description = "展示开始时间")
    @Column(name = "start_time")
    private Instant startTime;

    /**
     * 展示结束时间
     */
    @Schema(description = "展示结束时间")
    @Column(name = "end_time")
    private Instant endTime;

    /**
     * 地址\n多个使用竖杠|符合分隔
     */
    @Schema(description = "地址\n多个使用竖杠|符合分隔")
    @Column(name = "data_path")
    private String dataPath;

    /**
     * 封面图片（非图片类型需要）
     */
    @Schema(description = "封面图片（非图片类型需要）")
    @Column(name = "cover_pic_path")
    private String coverPicPath;

    /**
     * 文件大小
     */
    @Schema(description = "文件大小")
    @Size(max = 20)
    @Column(name = "data_size", length = 20)
    private String dataSize;

    /**
     * 文件md5值
     */
    @Schema(description = "文件md5值")
    @Column(name = "data_md")
    private String dataMd;

    /**
     * 文件后缀名
     */
    @Schema(description = "文件后缀名")
    @Size(max = 20)
    @Column(name = "data_suffix", length = 20)
    private String dataSuffix;

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

    @OneToMany(mappedBy = "resourceMetadata")
    @JsonIgnoreProperties(value = { "consumer", "planHeader", "resourceMetadata" }, allowSetters = true)
    private Set<PlanDetai> planDetais = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "planDetais", "resourceMetadata" }, allowSetters = true)
    private Consumer consumer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResourceMetadata id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ResourceMetadata name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public ResourceMetadata content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public ResourceMetadata resourceType(String resourceType) {
        this.setResourceType(resourceType);
        return this;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceMetadataType getDataType() {
        return this.dataType;
    }

    public ResourceMetadata dataType(ResourceMetadataType dataType) {
        this.setDataType(dataType);
        return this;
    }

    public void setDataType(ResourceMetadataType dataType) {
        this.dataType = dataType;
    }

    public Integer getShowTime() {
        return this.showTime;
    }

    public ResourceMetadata showTime(Integer showTime) {
        this.setShowTime(showTime);
        return this;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public Instant getStartTime() {
        return this.startTime;
    }

    public ResourceMetadata startTime(Instant startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

    public ResourceMetadata endTime(Instant endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getDataPath() {
        return this.dataPath;
    }

    public ResourceMetadata dataPath(String dataPath) {
        this.setDataPath(dataPath);
        return this;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getCoverPicPath() {
        return this.coverPicPath;
    }

    public ResourceMetadata coverPicPath(String coverPicPath) {
        this.setCoverPicPath(coverPicPath);
        return this;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.coverPicPath = coverPicPath;
    }

    public String getDataSize() {
        return this.dataSize;
    }

    public ResourceMetadata dataSize(String dataSize) {
        this.setDataSize(dataSize);
        return this;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getDataMd() {
        return this.dataMd;
    }

    public ResourceMetadata dataMd(String dataMd) {
        this.setDataMd(dataMd);
        return this;
    }

    public void setDataMd(String dataMd) {
        this.dataMd = dataMd;
    }

    public String getDataSuffix() {
        return this.dataSuffix;
    }

    public ResourceMetadata dataSuffix(String dataSuffix) {
        this.setDataSuffix(dataSuffix);
        return this;
    }

    public void setDataSuffix(String dataSuffix) {
        this.dataSuffix = dataSuffix;
    }

    public String getRemark() {
        return this.remark;
    }

    public ResourceMetadata remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ResourceMetadata createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public ResourceMetadata createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public ResourceMetadata lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public ResourceMetadata lastModifiedDate(Instant lastModifiedDate) {
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
            this.planDetais.forEach(i -> i.setResourceMetadata(null));
        }
        if (planDetais != null) {
            planDetais.forEach(i -> i.setResourceMetadata(this));
        }
        this.planDetais = planDetais;
    }

    public ResourceMetadata planDetais(Set<PlanDetai> planDetais) {
        this.setPlanDetais(planDetais);
        return this;
    }

    public ResourceMetadata addPlanDetai(PlanDetai planDetai) {
        this.planDetais.add(planDetai);
        planDetai.setResourceMetadata(this);
        return this;
    }

    public ResourceMetadata removePlanDetai(PlanDetai planDetai) {
        this.planDetais.remove(planDetai);
        planDetai.setResourceMetadata(null);
        return this;
    }

    public Consumer getConsumer() {
        return this.consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public ResourceMetadata consumer(Consumer consumer) {
        this.setConsumer(consumer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceMetadata)) {
            return false;
        }
        return id != null && id.equals(((ResourceMetadata) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceMetadata{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", content='" + getContent() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", showTime=" + getShowTime() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", dataPath='" + getDataPath() + "'" +
            ", coverPicPath='" + getCoverPicPath() + "'" +
            ", dataSize='" + getDataSize() + "'" +
            ", dataMd='" + getDataMd() + "'" +
            ", dataSuffix='" + getDataSuffix() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
