package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 17:01
 */
@AllArgsConstructor
@Getter
@Setter
public final class StatusSelectCommand implements Commandable {
    public static final String STATUS_SELECT_PREFIX = "STATUS_SELECT_PREFIX_";

    private final String showName;
    private String postfixParam;
}
