package com.summer.dev.standbot.constant.keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 01.06.2022
 * Time: 23:08
 */
public enum StandSelectCommands implements Commandable {
    STAND_SELECT;

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }
}
