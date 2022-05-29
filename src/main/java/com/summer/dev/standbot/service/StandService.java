package com.summer.dev.standbot.service;

import com.summer.dev.standbot.entity.Stand;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:45
 */
public interface StandService {
    Stand get(Long id);

    List<Stand> getAll();

    String getStandsInfo();

    String getStandInfo(String name);

    List<String> getStandsNames();

    void changeStatus(String standName, String equipmentName, String action);
}
