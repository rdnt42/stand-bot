package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.constant.keyboard.Commandable;
import com.summer.dev.standbot.constant.keyboard.MainMenuKeyboardCommandEnum;
import com.summer.dev.standbot.constant.keyboard.StandNameCommandEnum;
import com.summer.dev.standbot.service.bot.command.CommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:23
 */
@AllArgsConstructor
@Slf4j
@Service
public class CallbackQueryHandler {

    private final CommandService<MainMenuKeyboardCommandEnum> mainMenuCommandService;
    private final CommandService<StandNameCommandEnum> standNameCommandService;

    public SendMessage processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        SendMessage sendMessage = getMessageByCommand(data);
        sendMessage.setChatId(chatId);

        return sendMessage;
    }


    private SendMessage getMessageByCommand(String data) {
        Commandable command;
        if ((command = MainMenuKeyboardCommandEnum.getByName(data)) != null) {
            return mainMenuCommandService.getMessageFromCommand((MainMenuKeyboardCommandEnum) command);
        } else if ((command = StandNameCommandEnum.getByName(data)) != null) {
            return standNameCommandService.getMessageFromCommand((StandNameCommandEnum) command);
        }

        return mainMenuCommandService.getMessageFromCommand(MainMenuKeyboardCommandEnum.MAIN_MENU);
    }
}
