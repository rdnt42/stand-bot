package com.summer.dev.standbot.constant.keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:21
 */
public enum StandNameCommandEnum implements Commandable {
    STAND_NAME_;

    public static StandNameCommandEnum getByName(String name) {
        for (StandNameCommandEnum command : StandNameCommandEnum.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }

    public static boolean isContainStandName(String name) {
        return name.contains(STAND_NAME_.name());
    }
}
