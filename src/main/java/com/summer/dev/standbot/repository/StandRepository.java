package com.summer.dev.standbot.repository;

import com.summer.dev.standbot.entity.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:42
 */
@Repository
public interface StandRepository extends JpaRepository<Stand, Long> {

    @Query(nativeQuery = true,
    value = "select name from stands")
    List<String> getStandsNames();

    Optional<Stand> findByName(String name);
}
