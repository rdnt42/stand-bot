package com.summer.dev.standbot.constant.keyboard;

import com.summer.dev.standbot.constant.EquipmentStatuses;
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
    STATUS_TO_AVAILABLE(EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE.getDsc()),
    STATUS_TO_UNAVAILABLE(EquipmentStatuses.EQUIPMENT_STATE_UNAVAILABLE.getDsc()),
    STATUS_TO_UNSTABLE(EquipmentStatuses.EQUIPMENT_STATE_UNSTABLE.getDsc());

    private final String dsc;

    @Override
    public boolean isCommand(String command) {
        return command.equals(this.name());
    }

    public static StatusSelectCommands getByName(String name) {
        for (StatusSelectCommands command : StatusSelectCommands.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        throw new NullPointerException("StatusSelectCommands doesn't exists, name: " + name);
    }
}
