package com.summer.dev.standbot.service.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 12:57
 */
public interface KeyBoardService<T extends ReplyKeyboard> {
    T getMainMenuKeyBoard();

    T getStandSelectMenuKeyBoard();

    T getStandInfoMenuKeyBoard(String standName);

    T getEquipmentSelectForStandMenuKeyBoard(String standName);

    T getStatusSelectForEquipmentKeyBoard(String arguments);

    T getChangeStatusKeyBoard();

}
