package com.summer.dev.standbot.constant;

import com.vdurmont.emoji.EmojiParser;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 11:35
 */
@Getter
public enum TelegramEmoji {
    OK_MARK(":white_check_mark:"),
    ERROR_CHECK_MARK(":x:"),
    WARNING_CHECK_MARK(":warning:");

    private final String name;

    TelegramEmoji(String unicode) {
        this.name = unicode;
    }

    public String toUnicode() {
        return EmojiParser.parseToUnicode(name);
    }
}
