package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IndividualResponse {
    private Long id;
    private String bvn;

    @JsonProperty("jtb_tin")
    private String jtbTin;

    private String firstname;
    private String middlename;
    private String lastname;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    private String gender;
    private String title;

    @JsonProperty("tax_payer_photo")
    private String taxPayerPhoto;
    private String email;
    private String phone;

    @JsonProperty("phone_two")
    private String phoneTwo;

    @JsonProperty("marital_status")
    private String maritalStatus;

    @JsonProperty("state_of_origin")
    private String stateOfOrigin;

    @JsonProperty("house_no")
    private String houseNo;

    private String street;
    private String city;
    private String lga;

    @JsonProperty("state_of_residence")
    private String stateOfResidence;

    private String occupation;
    private String nationality;

    @JsonProperty("date_of_registration")
    private LocalDate dateOfRegistration;

    @JsonProperty("tax_authority_code")
    private String taxAuthorityCode;

    @JsonProperty("tax_authority_name")
    private String taxAuthorityName;

    @JsonProperty("tax_payer_status")
    private String taxPayerStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getJtbTin() {
        return jtbTin;
    }

    public void setJtbTin(String jtbTin) {
        this.jtbTin = jtbTin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxPayerPhoto() {
        return taxPayerPhoto;
    }

    public void setTaxPayerPhoto(String taxPayerPhoto) {
        this.taxPayerPhoto = taxPayerPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getStateOfResidence() {
        return stateOfResidence;
    }

    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getTaxAuthorityCode() {
        return taxAuthorityCode;
    }

    public void setTaxAuthorityCode(String taxAuthorityCode) {
        this.taxAuthorityCode = taxAuthorityCode;
    }

    public String getTaxAuthorityName() {
        return taxAuthorityName;
    }

    public void setTaxAuthorityName(String taxAuthorityName) {
        this.taxAuthorityName = taxAuthorityName;
    }

    public String getTaxPayerStatus() {
        return taxPayerStatus;
    }

    public void setTaxPayerStatus(String taxPayerStatus) {
        this.taxPayerStatus = taxPayerStatus;
    }
}
