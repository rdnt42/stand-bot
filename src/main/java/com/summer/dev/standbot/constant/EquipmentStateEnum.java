package com.summer.dev.standbot.constant;

import com.summer.dev.standbot.entity.EquipmentState;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 11:46
 */
@Getter
public enum EquipmentStateEnum {
    EQUIPMENT_STATE_AVAILABLE(1, "Доступен"),
    EQUIPMENT_STATE_NOT_AVAILABLE(2, "Недоступен"),
    EQUIPMENT_STATE_UNSTABLE(3, "Нестабилен");

    private final int id;
    private final String name;

    EquipmentStateEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equalsStatus(EquipmentState status) {
        return status.getId() == id;
    }
}
