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
public enum StatusSelectCommands {
    TO_AVAILABLE("Доступен"),
    TO_UNAVAILABLE("Недоступен"),
    TO_UNSTABLE("Сбои в работе");

    private final String dsc;
}
