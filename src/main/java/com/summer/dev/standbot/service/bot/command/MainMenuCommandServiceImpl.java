package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.MainMenuKeyboardCommand;
import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:14
 */
@AllArgsConstructor
@Service("mainMenuCommandService")
public class MainMenuCommandServiceImpl implements CommandService<MainMenuKeyboardCommand> {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(MainMenuKeyboardCommand command) {
        return switch (command) {
            case MAIN_MENU -> getMainMenuMessage();
            case STAND_SELECT -> getShowStandsStatesMessage();
        };
    }

    private SendMessage getMainMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(standService.getStandsInfo());
        sendMessage.setReplyMarkup(keyBoardService.getMainMenuKeyBoard());

        return sendMessage;
    }

    private SendMessage getShowStandsStatesMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите стенд*");
        sendMessage.setReplyMarkup(keyBoardService.getStandSelectMenuKeyBoard());

        return sendMessage;
    }
}
