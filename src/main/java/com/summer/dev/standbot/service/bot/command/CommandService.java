package com.summer.dev.standbot.service.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:04
 */
public interface CommandService {
    SendMessage getMessageFromCommand(String command);
}
