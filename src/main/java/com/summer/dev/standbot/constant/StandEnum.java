package com.summer.dev.standbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 13:49
 */
@Getter
@AllArgsConstructor
public enum StandEnum {
    STAND_B1(1, "B1"),
    STAND_B3(2, "B3"),
    STAND_B5(3, "B5"),
    STAND_SI1(4, "SI1"),
    STAND_SI3(5, "SI3");

    private final int id;
    private final String name;
}
