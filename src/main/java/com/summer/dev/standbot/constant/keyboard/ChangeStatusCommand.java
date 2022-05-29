package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 16:49
 */
@AllArgsConstructor
@Getter
@Setter
public final class ChangeStatusCommand implements Commandable {
    public static final String CHANGE_STATUS_PREFIX = "CHANGE_STATUS_PREFIX_";
    public static final String TO_AVAILABLE = "TO_AVAILABLE";
    public static final String TO_UNAVAILABLE = "TO_UNAVAILABLE";
    public static final String TO_UNSTABLE = "TO_UNSTABLE";

    public static final String TO_AVAILABLE_NAME = "Доступен";
    public static final String TO_UNAVAILABLE_NAME = "Недоступен";
    public static final String TO_UNSTABLE_NAME = "Сбои в работе";

    public static final String CHANGE_STATUS_NAME = "Изменить статус";

    private final String showName;
    private String postfixParam;
}
