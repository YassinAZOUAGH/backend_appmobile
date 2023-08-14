package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CommandRepository extends JpaRepository<Command, Long> {

    @Query("SELECT e FROM Command e WHERE e.date = :today")
    List<Command> findElementsAjoutesAujourdhui(@Param("today") String today);


    @Query("SELECT e FROM Command e WHERE e.date >= :startDate AND e.date <= :endDate")
    List<Command> findElementsAjoutesCetteSemaine(String startDate, String endDate);

    @Query("SELECT c FROM Command c WHERE c.date >= :startOfMonth AND c.date <= :endOfMonth")
    List<Command> findElementsAjoutesCeMois(String startOfMonth, String endOfMonth);

    @Query("SELECT c FROM Command c ORDER BY c.id DESC")
    List<Command> findAllCommandesOrdreInverse();

    @Query("SELECT hc, CASE WHEN ct.time IS NOT NULL THEN true ELSE false END AS traitee " +
            "FROM Command hc " +
            "LEFT JOIN CommandsToPrepare ct ON hc.time = ct.time")
    List<Object[]> findAllHistoriqueCommandesWithTraitementStatus();


}
