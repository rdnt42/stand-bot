package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.StandInfoCommand;
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
 * Date: 29.05.2022
 * Time: 17:51
 */
@AllArgsConstructor
@Service("standInfoCommandService")
public class StandInfoCommandServiceImpl implements CommandService {
    private final CommandParserService commandParserService;
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(String command) {
        String standName = commandParserService.parseStandName(StandInfoCommand.STAND_INFO_PREFIX, command);

        return getStandInfoMessage(standName);
    }

    private SendMessage getStandInfoMessage(String standName) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText(standService.getStandInfo(standName));
        sendMessage.setReplyMarkup(keyBoardService.getStandInfoMenuKeyBoard(standName));

        return sendMessage;
    }
}
