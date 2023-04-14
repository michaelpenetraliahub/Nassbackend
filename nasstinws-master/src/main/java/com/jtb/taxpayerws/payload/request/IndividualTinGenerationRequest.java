package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IndividualTinGenerationRequest{

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("bvn")
    private Int bvn;

    @NotNull(message = "{notnull.field}")
    @JsonProperty("nin")
    private Int nin;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("first_name")
    private String firstName;


    @NotNull(message = "{notnull.field}")
    @JsonProperty("middle_name")
    private String middleName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("date_of_birth")
    private LocalDate dob;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("gender")
    private String gender;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("photo")
    private String photo;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("phoneNo1")
    private String phone1;


    @NotNull(message = "{notnull.field}")
    @JsonProperty("phoneNo2")
    private String phone2;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("nationality")
    private String nationality;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("state_of_origin")
    private String stateOfOrigin;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("houseNo")
    private String houseNo;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("street_name")
    private String streetName;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("lga")
    private String lga;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("occupation")
    private String occupation;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("country")
    private String country;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("state")
    private String state;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Int getBvn() {
        return bvn;
    }

    public void setBvn(Int bvn) {
        this.bvn = bvn;
    }

    public Int getNin() {
        return nin;
    }

    public void setNin(Int nin) {
        this.nin = nin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phoneNo1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phoneNo2) {
        this.phone2 = phone2;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


}
