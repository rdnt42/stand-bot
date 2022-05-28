package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.constant.keyboard.*;
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

    private final CommandService<MainMenuKeyboardCommand> mainMenuCommandService;
    private final CommandService<StandSelectTemplateCommand> standSelectCommandService;
    private final CommandService<StandInfoCommand> standInfoCommandCommandService;

    public SendMessage processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        SendMessage sendMessage = getMessageByCommand(data);
        sendMessage.setChatId(chatId);

        return sendMessage;
    }


    private SendMessage getMessageByCommand(String data) {
        Commandable command;
        if ((command = MainMenuKeyboardCommand.getByName(data)) != null) {
            return mainMenuCommandService.getMessageFromCommand((MainMenuKeyboardCommand) command);
        } else if (StandSelectTemplateCommand.isContainsStandName(data)) {
            StandSelectTemplateCommand template = new StandSelectTemplateCommand(data);

            return standSelectCommandService.getMessageFromCommand(template);
        } else if ((command = StandInfoCommand.getByName(data)) != null) {
            return standInfoCommandCommandService.getMessageFromCommand((StandInfoCommand) command);
        }

            log.warn("Unknown command: {}. Return to main menu", data);
        return mainMenuCommandService.getMessageFromCommand(MainMenuKeyboardCommand.MAIN_MENU);
    }
}
