package com.summer.dev.standbot.service.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:04
 */
public interface CommandService {
    /**
     * @param command commands with arguments. Separated by delimiter
     * @return answer for that command
     */
    SendMessage getMessageFromCommand(String command);
}
