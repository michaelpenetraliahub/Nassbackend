package com.jtb.taxpayerws.dto;

import java.time.LocalDate;

public class NonIndividualTinGenerationDto{

    private Long id;

    private Int tin;

    private String name =  CACRegistrationName;

    private String organisationType;

    private LocalDate registrationDate;

    private LocalDate incorporationDate;

    private String lineOfBusinessName;

    private Int lineOfBusinessCode;

    private LocalDate commencementDate;

    private String directorName;

    private String directorPhone;

    private String directorEmail;

    private String email;

    private String financialYearBegin;

    private String financialYearEnd;


    private String RegNumber;


    private String phone1;


    private String phone2;


    private String streetName;

    private String city;

    private String lga;


    private String State;


    private String CACRegistrationName;


    private String officeNumber;




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

}
