package com.jtb.taxpayerws.entity;

import com.jtb.taxpayerws.entity.audit.Auditable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "asset_detail_non_individual")
public class NonIndividualAssetDetailEntity extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tin;
    private String location;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "asset_value")
    private String assetValue;

    @Column(name = "date_aquired")
    private LocalDate dateAcquired;
    private String description;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "non_individual_id", referencedColumnName = "id")
    private NonIndividualEntity nonIndividual;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private Long modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
    }

    public LocalDate getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(LocalDate dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NonIndividualEntity getNonIndividual() {
        return nonIndividual;
    }

    public void setNonIndividual(NonIndividualEntity nonIndividual) {
        this.nonIndividual = nonIndividual;
    }

    @Override
    public Long getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        super.setCreatedAt(createdAt);
    }

    @Override
    public Long getModifiedBy() {
        return super.getModifiedBy();
    }

    @Override
    public void setModifiedBy(Long modifiedBy) {
        super.setModifiedBy(modifiedBy);
    }

    @Override
    public LocalDateTime getModifiedAt() {
        return super.getModifiedAt();
    }

    @Override
    public void setModifiedAt(LocalDateTime modifiedAt) {
        super.setModifiedAt(modifiedAt);
    }
}
