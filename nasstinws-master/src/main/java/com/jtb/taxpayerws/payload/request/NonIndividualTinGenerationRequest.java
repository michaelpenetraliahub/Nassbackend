package com.jtb.taxpayerws.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;



public class NonIndividualTinGenerationRequest{

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("type_of_organisation")
    private String OrganisationType;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("registration_date")
    private LocalDate RegistrationDate;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("incorporation_date")
    private LocalDate IncorporationDate;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("line_of_business_name")
    private String lineOfBusinessName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("line_of_business_code")
    private Int lineOfBusinessCode;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("commencement_date")
    private LocalDate CommencementDate;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("director_name")
    private String directorName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("director_phone")
    private String directorPhone;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("director_email")
    private String directorEmail;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("financial_year_begin")
    private String FinancialYearBegin;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("financial_year_end")
    private String FinancialYearEnd;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("registration_number")
    private String RegNumber;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("phone_number_one")
    private String phone1;


    @NotNull(message = "{notnull.field}")
    @JsonProperty("phone_number_two")
    private String phon2;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("street_name")
    private String streetName;


    @NotNull(message = "{notnull.field}")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("lga")
    private String lga;


    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("state")
    private String State;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("cac_registration_name")
    private String CACRegistrationName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("office_number")
    private String OfficeNumber;




    public String getOrganisationType() {
        return OrganisationType;
    }

    public void setOrganisationType(String OrganisationType) {
        this.OrganisationType = OrganisationType;
    }


    public LocalDate getIncorporationDate() {
        return IncorporationDate;
    }

    public void setIncorporationDate(LocalDate IncorporationDate) {
        this.IncorporationDate = IncorporationDate;
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
        return CommencementDate;
    }

    public void setCommencementDate(LocalDate CommencementDate) {
        this.CommencementDate = CommencementDate;
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
        return FinancialYearBegin;
    }

    public void setFinancialYearBegin(String financialYearBegin) {
        this.FinancialYearBegin = financialYearBegin;
    }

    public String getFinancialYearEnd() {
        return FinancialYearEnd;
    }

    public void setFinancialYearEnd(String financialYearEnd) {
        this.FinancialYearEnd = financialYearEnd;
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

    public void setPhone1(String phoneNo1) {
        this.phone1 = phone1;
    }

    public String getPhoneNo2() {
        return phone2;
    }

    public void setPhoneNo2(String phoneTwo) {
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
        return OfficeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.OfficeNumber = officeNumber;
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
        return RegistrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.RegistrationDate = registrationDate;
    }


}
