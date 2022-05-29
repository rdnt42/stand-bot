package com.summer.dev.standbot.constant;

import com.summer.dev.standbot.entity.Status;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 11:46
 */
@Getter
public enum EquipmentStatusEnum {
    EQUIPMENT_STATE_AVAILABLE(1, "Доступен"),
    EQUIPMENT_STATE_UNAVAILABLE(2, "Недоступен"),
    EQUIPMENT_STATE_UNSTABLE(3, "Сбои в работе");

    private final int id;
    private final String name;

    EquipmentStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equalsStatus(Status status) {
        return status.getId() == id;
    }
}
