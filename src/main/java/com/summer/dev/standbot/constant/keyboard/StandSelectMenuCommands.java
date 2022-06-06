package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:26
 */
@AllArgsConstructor
@Getter
public enum StandSelectMenuCommands implements Commandable {
    STAND_SELECT_MENU("Выбор стенда");

    private final String dsc;
}
