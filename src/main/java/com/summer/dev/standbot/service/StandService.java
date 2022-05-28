package com.summer.dev.standbot.service;

import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.StandStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:45
 */
public interface StandService {
    Stand get(Long id);

    void setStatus(Long id, StandStatus status);

    List<Stand> getAll();

    String getStandsInfo();

    List<String> getStandsNames();
}
