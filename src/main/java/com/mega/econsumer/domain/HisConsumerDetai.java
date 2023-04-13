package com.mega.econsumer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mega.econsumer.domain.enumeration.ResourceMetadataType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * 消费历史明细\n场景1：计划生效后 记录生效的计划信息到历史表\n@author skyline
 */
@Schema(description = "消费历史明细\n场景1：计划生效后 记录生效的计划信息到历史表\n@author skyline")
@Entity
@Table(name = "ecs_his_consumer_detai")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HisConsumerDetai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 资源id
     */
    @Schema(description = "资源id")
    @Column(name = "resource_id")
    private String resourceId;

    /**
     * 主题
     */
    @Schema(description = "主题")
    @Size(max = 255)
    @Column(name = "name", length = 255)
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
    @JsonIgnoreProperties(value = { "hisConsumerDetais" }, allowSetters = true)
    private HisConsumerHeader hisConsumerHeader;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HisConsumerDetai id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public HisConsumerDetai resourceId(String resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return this.name;
    }

    public HisConsumerDetai name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public HisConsumerDetai content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public HisConsumerDetai resourceType(String resourceType) {
        this.setResourceType(resourceType);
        return this;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceMetadataType getDataType() {
        return this.dataType;
    }

    public HisConsumerDetai dataType(ResourceMetadataType dataType) {
        this.setDataType(dataType);
        return this;
    }

    public void setDataType(ResourceMetadataType dataType) {
        this.dataType = dataType;
    }

    public Integer getShowTime() {
        return this.showTime;
    }

    public HisConsumerDetai showTime(Integer showTime) {
        this.setShowTime(showTime);
        return this;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public String getDataPath() {
        return this.dataPath;
    }

    public HisConsumerDetai dataPath(String dataPath) {
        this.setDataPath(dataPath);
        return this;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getCoverPicPath() {
        return this.coverPicPath;
    }

    public HisConsumerDetai coverPicPath(String coverPicPath) {
        this.setCoverPicPath(coverPicPath);
        return this;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.coverPicPath = coverPicPath;
    }

    public String getDataSize() {
        return this.dataSize;
    }

    public HisConsumerDetai dataSize(String dataSize) {
        this.setDataSize(dataSize);
        return this;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getDataMd() {
        return this.dataMd;
    }

    public HisConsumerDetai dataMd(String dataMd) {
        this.setDataMd(dataMd);
        return this;
    }

    public void setDataMd(String dataMd) {
        this.dataMd = dataMd;
    }

    public String getDataSuffix() {
        return this.dataSuffix;
    }

    public HisConsumerDetai dataSuffix(String dataSuffix) {
        this.setDataSuffix(dataSuffix);
        return this;
    }

    public void setDataSuffix(String dataSuffix) {
        this.dataSuffix = dataSuffix;
    }

    public String getRemark() {
        return this.remark;
    }

    public HisConsumerDetai remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlanTime() {
        return this.planTime;
    }

    public HisConsumerDetai planTime(String planTime) {
        this.setPlanTime(planTime);
        return this;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public HisConsumerDetai createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public HisConsumerDetai createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public HisConsumerDetai lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public HisConsumerDetai lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public HisConsumerHeader getHisConsumerHeader() {
        return this.hisConsumerHeader;
    }

    public void setHisConsumerHeader(HisConsumerHeader hisConsumerHeader) {
        this.hisConsumerHeader = hisConsumerHeader;
    }

    public HisConsumerDetai hisConsumerHeader(HisConsumerHeader hisConsumerHeader) {
        this.setHisConsumerHeader(hisConsumerHeader);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HisConsumerDetai)) {
            return false;
        }
        return id != null && id.equals(((HisConsumerDetai) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HisConsumerDetai{" +
            "id=" + getId() +
            ", resourceId='" + getResourceId() + "'" +
            ", name='" + getName() + "'" +
            ", content='" + getContent() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", showTime=" + getShowTime() +
            ", dataPath='" + getDataPath() + "'" +
            ", coverPicPath='" + getCoverPicPath() + "'" +
            ", dataSize='" + getDataSize() + "'" +
            ", dataMd='" + getDataMd() + "'" +
            ", dataSuffix='" + getDataSuffix() + "'" +
            ", remark='" + getRemark() + "'" +
            ", planTime='" + getPlanTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
