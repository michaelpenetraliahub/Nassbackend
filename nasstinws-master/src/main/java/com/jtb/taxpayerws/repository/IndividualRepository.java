package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.IndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;



public interface IndividualRepository extends JpaRepository<IndividualEntity, Long>, JpaSpecificationExecutor<IndividualEntity> {
    @Query(value = "SELECT * FROM individual i WHERE i.jtb_tin LIKE :search"
            + " OR i.phone LIKE :search"
            + " OR i.email LIKE :search"
            + " OR i.phone_two LIKE :search", nativeQuery = true)
    IndividualEntity findIndividualByKeyword(@Param("search") String search);

    @Query(value = "SELECT count(*)  FROM individual AS i " +
            "WHERE EXTRACT(MONTH FROM i.date_of_registration) = :month " +
            "AND date_trunc('year', i.date_of_registration) = date_trunc('year', now())",
            nativeQuery = true)
    Long countRecordsByMonth(@Param("month") int month);

    Long countByDateOfRegistration(LocalDate date);

    IndividualEntity findByJtbTin(String tin);


}
