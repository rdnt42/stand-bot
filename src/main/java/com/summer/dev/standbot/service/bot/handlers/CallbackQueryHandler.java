package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.constant.keyboard.*;
import com.summer.dev.standbot.service.bot.command.CommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("mainMenuCommandService")
    private final CommandService mainMenuCommandService;

    @Qualifier("standSelectMenuCommandService")
    private final CommandService standSelectMenuCommandService;

    @Qualifier("standSelectCommandService")
    private final CommandService standSelectCommandService;

    @Qualifier("equipmentSelectCommandService")
    private final CommandService equipmentSelectCommandService;

    @Qualifier("statusSelectCommandService")
    private final CommandService statusSelectCommandService;

    @Qualifier("changeStatusCommandService")
    private final CommandService changeStatusCommandService;

    public SendMessage processCallbackQuery(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        SendMessage sendMessage = trySendMessageByCommand(data);
        sendMessage.setChatId(chatId);
        log.debug("Send answer to chatId: {}, name: {}",
                chatId, buttonQuery.getFrom().getFirstName());

        return sendMessage;
    }

    private SendMessage trySendMessageByCommand(String data) {
        try {
            return getMessageByCommand(data);
        } catch (Exception e) {
            e.printStackTrace();

            return mainMenuCommandService.getMessageFromCommand(MainMenuCommands.MAIN_MENU.name());
        }
    }

    private SendMessage getMessageByCommand(String command) {
        log.debug("Got command: " + command);
        if (Commandable.isCommand(MainMenuCommands.MAIN_MENU.name(), command)) {
            return mainMenuCommandService.getMessageFromCommand(command);
        } else if (Commandable.isCommand(StandSelectMenuCommands.STAND_SELECT_MENU.name(), command)) {
            return standSelectMenuCommandService.getMessageFromCommand(command);
        } else if (Commandable.isCommand(StandSelectCommands.STAND_SELECT.name(), command)) {
            return standSelectCommandService.getMessageFromCommand(command);
        }
//        else if (Commandable.isCommand(EquipmentSelectCommand.EQUIPMENT_SELECT_PREFIX, command)) {
//            return equipmentSelectCommandService.getMessageFromCommand(command);
//        } else if (Commandable.isCommand(StatusSelectCommand.STATUS_SELECT_PREFIX, command)) {
//            return statusSelectCommandService.getMessageFromCommand(command);
//        } else if (Commandable.isCommand(ChangeStatusCommand.CHANGE_STATUS_PREFIX, command)) {
//            return changeStatusCommandService.getMessageFromCommand(command);
//        }


        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
