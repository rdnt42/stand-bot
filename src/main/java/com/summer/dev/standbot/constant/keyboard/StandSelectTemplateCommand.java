package com.summer.dev.standbot.constant.keyboard;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 20:03
 */
@Getter
@Setter
public final class StandSelectTemplateCommand implements Commandable {
    public static final String STAND_NAME_PREFIX = "STAND_NAME_PREFIX_";
    private String standName;

    public StandSelectTemplateCommand(String commandName) {
        this.standName = commandName.replace(STAND_NAME_PREFIX, "");
    }

    public static boolean isContainsStandName(String name) {
        return name.contains(STAND_NAME_PREFIX);
    }
}
