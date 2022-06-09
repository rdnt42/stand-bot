package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 01.06.2022
 * Time: 23:10
 */
@AllArgsConstructor
@Getter
public enum StatusSelectMenuCommands implements Commandable {
    STATUS_SELECT_MENU("Изменить статус");

    private final String dsc;

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }
}
