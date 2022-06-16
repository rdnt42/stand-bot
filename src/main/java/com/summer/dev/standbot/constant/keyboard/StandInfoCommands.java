package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:25
 */
@AllArgsConstructor
@Getter
public enum StandInfoCommands implements Commandable {
    STAND_INFO("Информация о стенде");

    private final String dsc;

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }}
