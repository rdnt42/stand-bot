package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:34
 */
@Getter
@AllArgsConstructor
public enum MainMenuKeyboardCommandEnum implements Commandable {
    SHOW_STATUS("Статус"),
    CHANGE_STATUS("Изменить статус"),
    MAIN_MENU("Основное меню");

    private final String showName;

    public static MainMenuKeyboardCommandEnum getByName(String name) {
        for (MainMenuKeyboardCommandEnum command : MainMenuKeyboardCommandEnum.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }
}
