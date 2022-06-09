package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.constant.keyboard.*;
import com.summer.dev.standbot.service.bot.command.CommandService;
import com.summer.dev.standbot.service.bot.command.CommandServiceFactory;
import com.summer.dev.standbot.service.bot.command.parser.CommandParserService;
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

    private final CommandServiceFactory commandServiceFactory;
    private final CommandParserService commandParserService;


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

            String mainMenu = MainMenuCommands.MAIN_MENU.name();
            CommandService commandService = commandServiceFactory.getCommandService(mainMenu);

            return commandService.getMessageFromCommand(mainMenu);
        }
    }

    private SendMessage getMessageByCommand(String command) {
        log.debug("Full command: " + command);

        String firstCommand = commandParserService.getFirstCommand(command);
        CommandService commandService = commandServiceFactory.getCommandService(firstCommand);

        return commandService.getMessageFromCommand(command);
    }
}
