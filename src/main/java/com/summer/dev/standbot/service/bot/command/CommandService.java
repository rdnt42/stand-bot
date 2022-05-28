package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.Commandable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:04
 */
public interface CommandService<T extends Commandable> {
    SendMessage getMessageFromCommand(T command);
}
