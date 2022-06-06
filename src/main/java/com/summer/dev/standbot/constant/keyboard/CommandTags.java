package com.summer.dev.standbot.constant.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2022
 * Time: 0:05
 */
@Getter
@AllArgsConstructor
public enum CommandTags {
    TAG_END(";");

    private final String tag;
}
