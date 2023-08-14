package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.CommandsToPrepare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandsToPrepareRepository extends JpaRepository<CommandsToPrepare, Long> {

     /*@Query("SELECT CASE WHEN COUNT(t1) > 0 AND COUNT(t2) > 0 THEN true ELSE false END " +
            "FROM Command t1, CommandsToPrepare t2 " +
            "WHERE t1.contents = :contents AND t1.time = :time " +
            "AND t2.contents = :contents AND t2.time = :time")
    boolean existsInBothTables(@Param("contents") String contents, @Param("time") String time);*/



}
