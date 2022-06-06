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
    CHANGE_STATUS_TO_AVAILABLE("Доступен"),
    CHANGE_STATUS_TO_UNAVAILABLE("Недоступен"),
    CHANGE_STATUS_TO_UNSTABLE("Сбои в работе");

    private final String dsc;
}
