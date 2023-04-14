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

Entity(name = "individual_tin_generation")
public class IndividualTinGenerationEntity extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tin",nullable = false, length = 200)
    private Int tin;

    @Column(name = "name",nullable = false, length = 400)
    private String name;

    @Column(name = "title",nullable = false, length = 200)
    private String title;

    @Column(name = "bvn",nullable = false, length = 200)
    private Int bvn;

    @Column(name = "nin",nullable = false, length = 200)
    private Int nin;

    @Column(name = "first_name",nullable = false, length = 200)
    private String firstName;

    @Column(name = "middle_name",nullable = false, length = 200)
    private String middleName;

    @Column(name = "last_name",nullable = false, length = 200)
    private String lastName;

    @Column(name = "date_of_birth",nullable = false, length = 200)
    private LocalDate dob;

    @Column(name="gender",nullable = false, length = 200)
    private String gender;
    @Column(name="title",nullable = false, length = 200)
    private String title;

    @Column(name = "tax_payer_photo",nullable = false, length = 200)
    private String photo;

    @Column(name="email",nullable = false, length = 200)
    private String email;

    @Column(name="phone_one",nullable = false, length = 200)
    private String phone1;

    @Column(name = "phone_two",nullable = false, length = 200)
    private String phone2;

    @Column(name = "nationality",nullable = false, length = 200)
    private String nationality;

    @Column(name = "state_of_origin",nullable = false, length = 200)
    private String stateOfOrigin;

    @Column(name = "house_No",nullable = false, length = 200)
    private String houseNo;

    @Column(name="street_name",nullable = false, length = 200)
    private String streetName;

    @Column(name="city_name",nullable = false, length = 200)
    private String city;

    @Column(name="lga",nullable = false, length = 200)
    private String lga;

    @Column(name="occupation",nullable = false, length = 200)
    private String occupation;

    @Column(name="nationality",nullable = false, length = 200)
    private String nationality;

    @Column(name="country",nullable = false, length = 200)
    private String country;

    @Column(name="state",nullable = false, length = 200)
    private String state;



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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
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
