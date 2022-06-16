package com.summer.dev.standbot.repository;

import com.summer.dev.standbot.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 19:57
 */
public interface StatusRepository extends JpaRepository<Status, Long> {
}
