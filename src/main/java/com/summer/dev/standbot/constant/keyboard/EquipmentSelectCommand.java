package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 23:57
 */
@AllArgsConstructor
@Getter
public enum EquipmentSelectCommand implements Commandable {
    STATUS_STAND("Статус стенда"),
    STATUS_METRIC("Статус метрик"),
    STATUS_INDEPENDENT_SESSION("Статус независимой сессии"),
    STATUS_DEPENDENT_SESSION("Статус зависимой сессии")
    ;
    private final String showName;

    public static EquipmentSelectCommand getByName(String name) {
        for (EquipmentSelectCommand command : EquipmentSelectCommand.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }
}
