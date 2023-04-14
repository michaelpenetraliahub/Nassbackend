package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.NonIndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface NonIndividualRepository extends JpaRepository<NonIndividualEntity, Long>, JpaSpecificationExecutor<NonIndividualEntity> {
    @Query(value = "SELECT * FROM non_individual i WHERE i.current_tin LIKE :search"
            + " OR i.phone LIKE :search"
            + " OR i.email_address LIKE :search"
            + " OR i.phone_two LIKE :search", nativeQuery = true)
    NonIndividualEntity findNonIndividualByKeyword(@Param("search") String search);


    @Query(value = "SELECT count(*)  FROM non_individual AS i " +
            "WHERE EXTRACT(MONTH FROM i.date_of_registration) = :month " +
            "AND date_trunc('year', i.date_of_registration) = date_trunc('year', now())",
            nativeQuery = true)
    Long countRecordsByMonth(@Param("month") int month);

    Long countByDateOfRegistration(LocalDate date);

    NonIndividualEntity findByCurrentTin(String tin);
}
