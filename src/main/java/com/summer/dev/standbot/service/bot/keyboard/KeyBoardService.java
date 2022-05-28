package com.summer.dev.standbot.service.bot.keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 12:57
 */
public interface KeyBoardService <T>{
    T getMainMenuKeyBoard();

    T getStandsNamesKeyBoard();
}
