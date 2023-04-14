package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NonIndividualResponse {
    private Long id;

    @JsonProperty("registration_name")
    private String registrationName;

    @JsonProperty("main_trade_name")
    private String mainTradeName;

    @JsonProperty("type_of_organisation")
    private String typeOfOrganisation;

    @JsonProperty("current_tin")
    private String currentTin;

    @JsonProperty("previous_tin")
    private String previousTin;

    @JsonProperty("insurance_place")
    private String insurancePlace;

    @JsonProperty("date_of_registration")
    private LocalDate dateOfRegistration;

    @JsonProperty("date_of_incorporation")
    private LocalDate dateOfIncorporation;

    @JsonProperty("line_of_business")
    private String lineOfBusiness;

    private String sector;
    private String ward;
    private String state;

    @JsonProperty("date_of_commencement")
    private LocalDate dateOfCommencement;

    @JsonProperty("director_name")
    private String directorName;

    @JsonProperty("director_phone")
    private String directorPhone;

    @JsonProperty("director_email")
    private String directorEmail;

    @JsonProperty("trade_office")
    private String tradeOffice;

    @JsonProperty("tax_authority")
    private String taxAuthority;

    @JsonProperty("tax_authority_code")
    private String taxAuthorityCode;

    @JsonProperty("tax_authority_name")
    private String taxAuthorityName;

    @JsonProperty("tax_payer_status")
    private String taxPayerStatus;

    @JsonProperty("email_address")
    private String emailAddress;

    private String country;

    @JsonProperty("fin_year_begin")
    private String finYearBegin;

    @JsonProperty("fin_year_end")
    private String finYearEnd;

    @JsonProperty("registration_number")
    private String registrationNumber;

    private String phone;

    @JsonProperty("phone_two")
    private String phoneTwo;

    @JsonProperty("state_of_origin")
    private String stateOfOrigin;

    @JsonProperty("house_no")
    private String houseNo;

    @JsonProperty("door_no")
    private String doorNo;

    private String street;
    private String city;
    private String lga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getMainTradeName() {
        return mainTradeName;
    }

    public void setMainTradeName(String mainTradeName) {
        this.mainTradeName = mainTradeName;
    }

    public String getTypeOfOrganisation() {
        return typeOfOrganisation;
    }

    public void setTypeOfOrganisation(String typeOfOrganisation) {
        this.typeOfOrganisation = typeOfOrganisation;
    }

    public String getCurrentTin() {
        return currentTin;
    }

    public void setCurrentTin(String currentTin) {
        this.currentTin = currentTin;
    }

    public String getPreviousTin() {
        return previousTin;
    }

    public void setPreviousTin(String previousTin) {
        this.previousTin = previousTin;
    }

    public String getInsurancePlace() {
        return insurancePlace;
    }

    public void setInsurancePlace(String insurancePlace) {
        this.insurancePlace = insurancePlace;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public LocalDate getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(LocalDate dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDateOfCommencement() {
        return dateOfCommencement;
    }

    public void setDateOfCommencement(LocalDate dateOfCommencement) {
        this.dateOfCommencement = dateOfCommencement;
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

    public String getTradeOffice() {
        return tradeOffice;
    }

    public void setTradeOffice(String tradeOffice) {
        this.tradeOffice = tradeOffice;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFinYearBegin() {
        return finYearBegin;
    }

    public void setFinYearBegin(String finYearBegin) {
        this.finYearBegin = finYearBegin;
    }

    public String getFinYearEnd() {
        return finYearEnd;
    }

    public void setFinYearEnd(String finYearEnd) {
        this.finYearEnd = finYearEnd;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
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
}
