package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.service.bot.command.parser.CommandParserService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 18:57
 */
@AllArgsConstructor
@Service("statusSelectCommandService")
public class StatusSelectCommandServiceImpl implements CommandService {
    private final CommandParserService commandParserService;
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;


    @Override
    public SendMessage getMessageFromCommand(String command) {
        String selectCommand = commandParserService.getFirstCommand(command);
        String equipmentCommand = commandParserService.getNextCommand(command, selectCommand);
        String standCommand = commandParserService.getNextCommand(command, equipmentCommand);

        return getChangeStatusMenuMessage(equipmentCommand, standCommand);
    }

    private SendMessage getChangeStatusMenuMessage(String equipmentCommand, String standCommand) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите новый статус*");
        sendMessage.setReplyMarkup(keyBoardService.getStatusSelectForEquipmentKeyBoard(equipmentCommand, standCommand));

        return sendMessage;
    }
}
