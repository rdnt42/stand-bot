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
public final class StandSelectCommand implements Commandable {
    public static final String STAND_SELECT = "STAND_SELECT";

    public static final String STAND_SELECT_NAME = "Выбор стенда";
}
