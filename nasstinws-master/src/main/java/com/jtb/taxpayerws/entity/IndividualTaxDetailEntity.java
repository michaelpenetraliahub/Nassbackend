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

@Entity(name = "tax_detail_individual")
public class IndividualTaxDetailEntity extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jtbTin;
    private String oldTin;
    private String taxNumber;
    private String taxPeriod;
    private String assessableProfit;
    private String totalProfit;
    private String taxPayable;
    private String taxPaid;
    private LocalDate paymentDate;
    private String taxType;
    private String taxOffice;
    private String taxAuthority;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "individual_id", referencedColumnName = "id")
    private IndividualEntity individual;

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

    public String getJtbTin() {
        return jtbTin;
    }

    public void setJtbTin(String jtbTin) {
        this.jtbTin = jtbTin;
    }

    public String getOldTin() {
        return oldTin;
    }

    public void setOldTin(String oldTin) {
        this.oldTin = oldTin;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(String taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public String getAssessableProfit() {
        return assessableProfit;
    }

    public void setAssessableProfit(String assessableProfit) {
        this.assessableProfit = assessableProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(String taxPayable) {
        this.taxPayable = taxPayable;
    }

    public String getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(String taxPaid) {
        this.taxPaid = taxPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate payementDate) {
        this.paymentDate = payementDate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public IndividualEntity getIndividual() {
        return individual;
    }

    public void setIndividual(IndividualEntity individual) {
        this.individual = individual;
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
