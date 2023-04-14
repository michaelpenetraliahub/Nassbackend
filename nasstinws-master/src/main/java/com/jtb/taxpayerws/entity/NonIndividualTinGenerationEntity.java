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

@Entity(name = "non_individual_tin_generation")
public class NonIndividualTinGenerationEntity extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tin",nullable = false, length = 200)
    private Int tin;

    @Column(name = "name",nullable = false, length = 200)
    private String name;

    @Column(name = "type_of_organisation",nullable = false, length = 200)
    private String organisationType;

    @Column(name = "date_of_registration",nullable = false, length = 200)
    private LocalDate registrationDate;

    @Column(name = "date_of_incorporation",nullable = false, length = 200)
    private LocalDate incorporationDate;

    @Column(name = "line_of_business_name",nullable = false, length = 200)
    private String lineOfBusinessName;

    @Column(name="line_of_business_code",nullable = false, length = 200)
    private Int lineOfBusinessCode;

    @Column(name = "date_of_commencement",nullable = false, length = 200)
    private LocalDate commencementDate;

    @Column(name = "director_name",nullable = false, length = 200)
    private String directorName;

    @Column(name = "director_phone",nullable = false, length = 200)
    private String directorPhone;

    @Column(name = "director_email",nullable = false, length = 200)
    private String directorEmail;


    @Column(name = "email_address",nullable = false, length = 200)
    private String email;


    @Column(name = "fin_year_begin",nullable = false, length = 200)
    private String financialYearBegin;

    @Column(name = "fin_year_end",nullable = false, length = 200)
    private String financialYearEnd;

    @Column(name = "registration_number",nullable = false, length = 200)
    private String RegNumber;

    @Column(name="phone_one",nullable = false, length = 200)
    private String phone1;

    @Column(name = "phone_two",nullable = false, length = 200)
    private String phone2;

    @Column(name="street_name",nullable = false, length = 200)
    private String streetName;
    @Column(name="city_name",nullable = false, length = 200)
    private String city;
    @Column(name="lga_name",nullable = false, length = 200)
    private String lga;

    @Column(name="state_name",nullable = false, length = 200)
    private String State;

    @Column(name="cac_registration_name",nullable = false, length = 200)
    private String CACRegistrationName;


    @Column(name="office_number",nullable = false, length = 200)
    private String officeNumber;

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


    public Int getTin() {
        return tin;
    }


    public void setTin(Int tin) {
        this.tin = tin;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getOrganisationType() {
        return organisationType;
    }


    public void setOrganisationType(String organisationType) {
        this.organisationType = organisationType;
    }


    public LocalDate getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(LocalDate incorporationDate) {
        this.incorporationDate = incorporationDate;
    }

    public String getLineOfBusinessName() {
        return lineOfBusinessName;
    }

    public void setLineOfBusinessName(String lineOfBusinessName) {
        this.lineOfBusinessName = lineOfBusinessName;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(LocalDate commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorPhone() {
        return directorPhone;
    }

    public void setDirectorPhone(String directorPhone) {
        this.directorPhone = directorPhone;
    }

    public String getDirectorEmail() {
        return directorEmail;
    }

    public void setDirectorEmail(String directorEmail) {
        this.directorEmail = directorEmail;
    }

p

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFinancialYearBegin() {
        return financialYearBegin;
    }

    public void setFinancialYearBegin(String financialYearBegin) {
        this.financialYearBegin = financialYearBegin;
    }

    public String getFinancialYearEnd() {
        return financialYearEnd;
    }

    public void setFinancialYearEnd(String financialYearEnd) {
        this.financialYearEnd = financialYearEnd;
    }

    public String getRegNumber() {
        return RegNumber;
    }

    public void setRegNumber(String RegNumber) {
        this.RegNumber = RegNumber;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }


    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String street) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getCACRegistrationName() {
        return CACRegistrationName;
    }

    public void setCACRegistrationName(String CACRegistrationName) {
        this.CACRegistrationName = CACRegistrationName;
    }

    public void setLineOfBusinessCode(Int lineOfBusinessCode) {
        this.lineOfBusinessCode = lineOfBusinessCode;
    }

    public Int getLineOfBusinessCode(){
        return lineOfBusinessCode;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
       this.registrationDate = registrationDate;
    }

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Long getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}
