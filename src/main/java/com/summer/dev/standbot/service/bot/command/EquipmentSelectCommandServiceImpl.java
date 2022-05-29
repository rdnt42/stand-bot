package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.EquipmentSelectCommand;
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
 * Time: 18:20
 */
@AllArgsConstructor
@Service("equipmentSelectCommandService")
public class EquipmentSelectCommandServiceImpl implements CommandService {
    private final CommandParserService commandParserService;
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;

    @Override
    public SendMessage getMessageFromCommand(String command) {
        String standName = commandParserService.parseStandName(EquipmentSelectCommand.EQUIPMENT_SELECT_PREFIX, command);

        return getEquipmentSelectForStandMessage(standName);
    }

    private SendMessage getEquipmentSelectForStandMessage(String standName) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите параметр для изменения*");
        sendMessage.setReplyMarkup(keyBoardService.getEquipmentSelectForStandMenuKeyBoard(standName));

        return sendMessage;
    }
}