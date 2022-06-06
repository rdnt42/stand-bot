package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:22
 */
@AllArgsConstructor
@Getter
public enum EquipmentSelectCommands implements Commandable {
    STAND("Стенд"),
    METRIC("Метрики"),
    INDEPENDENT_SESSION("Независимая сессия"),
    DEPENDENT_SESSION("Зависимая сессия");

    private final String dsc;
}
