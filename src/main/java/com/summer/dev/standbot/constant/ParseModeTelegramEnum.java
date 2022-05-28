package com.summer.dev.standbot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 23:51
 */
@AllArgsConstructor
@Getter
public enum ParseModeTelegramEnum {
    PARSE_MODE_MARKDOWN("Markdown");

    private String name;
}
