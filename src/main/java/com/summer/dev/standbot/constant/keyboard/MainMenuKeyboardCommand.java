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
public enum MainMenuKeyboardCommand implements Commandable {
    STAND_SELECT("Выбор стенда"),
    MAIN_MENU("Основное меню");

    private final String showName;

    public static MainMenuKeyboardCommand getByName(String name) {
        for (MainMenuKeyboardCommand command : MainMenuKeyboardCommand.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }
}
