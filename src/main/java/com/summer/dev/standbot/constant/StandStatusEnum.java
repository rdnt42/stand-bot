package com.summer.dev.standbot.constant;

import com.summer.dev.standbot.entity.StandStatus;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 11:46
 */
@Getter
public enum StandStatusEnum {
    STAND_STATUS_SUCCESS(1, "SUCCESS"),
    STAND_STATUS_WARNING(2, "WARNING"),
    STAND_STATUS_ERROR(3, "ERROR");

    private final int id;
    private final String name;

    StandStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equalsStatus(StandStatus status) {
        return status.getId() == id;
    }
}
