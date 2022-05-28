package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.MainMenuKeyboardCommandEnum;
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
public class MainMenuCommandServiceImpl implements CommandService<MainMenuKeyboardCommandEnum> {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(MainMenuKeyboardCommandEnum command) {
        return switch (command) {
            case MAIN_MENU -> getMainMenuMessage();
            case SHOW_STATUS -> getShowStandsStatusesMessage();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    private SendMessage getMainMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(standService.getStandsInfo());
        sendMessage.setReplyMarkup(keyBoardService.getMainMenuKeyBoard());

        return sendMessage;
    }

    private SendMessage getShowStandsStatusesMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите стенд");
        sendMessage.setReplyMarkup(keyBoardService.getStandsNamesKeyBoard());

        return sendMessage;
    }
}
