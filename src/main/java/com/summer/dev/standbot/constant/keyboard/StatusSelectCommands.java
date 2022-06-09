package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:18
 */
@AllArgsConstructor
@Getter
public enum StatusSelectCommands implements Commandable {
    STATUS_TO_AVAILABLE("Доступен"),
    STATUS_TO_UNAVAILABLE("Недоступен"),
    STATUS_TO_UNSTABLE("Сбои в работе");

    private final String dsc;

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }
}
