package com.summer.dev.standbot.service;

import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.EquipmentState;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:45
 */
public interface StandService {
    Stand get(Long id);

    void setStatus(Long id, EquipmentState status);

    List<Stand> getAll();

    String getStandsInfo();

    String getStandInfo(String name);

    List<String> getStandsNames();
}
