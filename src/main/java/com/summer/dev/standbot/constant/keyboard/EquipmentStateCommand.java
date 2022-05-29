package com.summer.dev.standbot.constant.keyboard;

import com.summer.dev.standbot.constant.EquipmentStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 15:04
 */
@AllArgsConstructor
@Getter
public enum EquipmentStateCommand implements Commandable {
    CHANGE_TO_AVAILABLE(EquipmentStateEnum.EQUIPMENT_STATE_AVAILABLE.getName()),
    CHANGE_TO_UNAVAILABLE(EquipmentStateEnum.EQUIPMENT_STATE_UNAVAILABLE.getName()),
    CHANGE_TO_UNSTABLE(EquipmentStateEnum.EQUIPMENT_STATE_UNSTABLE.getName());

    private final String showName;

    public static EquipmentStateCommand getByName(String name) {
        for (EquipmentStateCommand command : EquipmentStateCommand.values()) {
            if(command.name().equals(name)) {
                return command;
            }
        }

        return null;
    }
}
