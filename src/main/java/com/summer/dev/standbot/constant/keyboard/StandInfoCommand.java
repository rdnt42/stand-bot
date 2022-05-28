package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 23:36
 */
@Getter
@AllArgsConstructor
public enum StandInfoCommand implements Commandable {
    CHANGE_STATUS("Изменить статус");

    private final String showName;

    public static StandInfoCommand getByName(String name) {
        for (StandInfoCommand command : StandInfoCommand.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }
}
