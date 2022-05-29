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
    private final CommandService<EquipmentStateCommand> equipmentStateCommandCommandService;

    public SendMessage processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        SendMessage sendMessage = trySendMessageByCommand(data);
        sendMessage.setChatId(chatId);

        return sendMessage;
    }

    private SendMessage trySendMessageByCommand(String data) {
        try {
            return getMessageByCommand(data);
        } catch (Exception e) {
            e.printStackTrace();
            return mainMenuCommandService.getMessageFromCommand(MainMenuKeyboardCommand.MAIN_MENU);
        }
    }

    private SendMessage getMessageByCommand(String data) {
        Commandable command;
        if ((command = MainMenuKeyboardCommand.getByName(data)) != null) {
            return getMessageFromMainMenu(command);
        } else if (StandSelectTemplateCommand.isContainsStandName(data)) {
            return getMessageFromStandSelect(data);
        } else if ((command = StandInfoCommand.getByName(data)) != null) {
            return getMessageFromStandInfo(command);
        } else if ((command = EquipmentStateCommand.getByName(data)) != null) {
            return getMessageFromEquipmentState(command);
        } else {
            throw new IllegalArgumentException("Unknown command: " + data);
        }
    }

    private SendMessage getMessageFromMainMenu(Commandable command) {
        return mainMenuCommandService.getMessageFromCommand((MainMenuKeyboardCommand) command);
    }

    private SendMessage getMessageFromStandSelect(String data) {
        StandSelectTemplateCommand template = new StandSelectTemplateCommand(data);

        return standSelectCommandService.getMessageFromCommand(template);
    }

    private SendMessage getMessageFromStandInfo(Commandable command) {
        return standInfoCommandCommandService.getMessageFromCommand((StandInfoCommand) command);
    }

    private SendMessage getMessageFromEquipmentState(Commandable command) {
        return equipmentStateCommandCommandService.getMessageFromCommand((EquipmentStateCommand) command);
    }
}
