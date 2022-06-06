package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.StandSelectCommands;
import com.summer.dev.standbot.constant.keyboard.StandSelectMenuCommands;
import com.summer.dev.standbot.service.bot.command.parser.CommandParserService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 01.06.2022
 * Time: 23:47
 */
@AllArgsConstructor
@Service("standSelectMenuCommandService")
public class StandSelectMenuCommandServiceImpl implements CommandService {

    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final CommandParserService commandParserService;

    @Override
    public SendMessage getMessageFromCommand(String command) {
        String parseCommand = commandParserService.parseCommand(command);

        if (StandSelectMenuCommands.STAND_SELECT_MENU.name().equals(parseCommand)) {
            return getSelectStandsMenu();
        }

        throw new IllegalStateException("Unexpected value: " + command);
    }

    private SendMessage getSelectStandsMenu() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите стенд*");
        sendMessage.setReplyMarkup(keyBoardService.getStandSelectMenuKeyBoard());

        return sendMessage;
    }
}
