package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:09
 */
@AllArgsConstructor
@Getter
public enum ChangeStatusCommands implements Commandable {
    CHANGE_STATUS();

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }
}
