package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.StandSelectCommand;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:57
 */
@Slf4j
@AllArgsConstructor
@Service("standSelectCommandService")
public class StandSelectCommandServiceImpl implements CommandService {

    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;

    @Override
    public SendMessage getMessageFromCommand(String command) {
        if (StandSelectCommand.STAND_SELECT.equals(command)) {
            return getShowStandsStatesMessage();
        }

        throw new IllegalStateException("Unexpected value: " + command);
    }

    private SendMessage getShowStandsStatesMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите стенд*");
        sendMessage.setReplyMarkup(keyBoardService.getStandSelectMenuKeyBoard());

        return sendMessage;
    }
}
