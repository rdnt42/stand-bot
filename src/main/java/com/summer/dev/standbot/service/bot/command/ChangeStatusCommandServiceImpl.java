package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.command.parser.CommandParserService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 23:43
 */
@AllArgsConstructor
@Service("changeStatusCommandService")
public class ChangeStatusCommandServiceImpl implements CommandService {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final CommandParserService commandParserService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(String command) {
        updateStandStatus(command);

        return getChangeStatusMenuMessage();
    }

    private void updateStandStatus(String command) {
        String standName = commandParserService.parseStandNameByPattern(command);
        String equipmentName = commandParserService.parseEquipmentNameByPattern(command);
        String action = commandParserService.parseActionNameByPattern(command);
        standService.changeStatus(standName, equipmentName, action);
    }

    private SendMessage getChangeStatusMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Статус успешно обновлен*");
        sendMessage.setReplyMarkup(keyBoardService.getChangeStatusKeyBoard());

        return sendMessage;
    }

}
