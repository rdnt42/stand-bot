package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.MainMenuCommands;
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
 * Time: 14:14
 */
@AllArgsConstructor
@Service("mainMenuCommandService")
public class MainMenuCommandServiceImpl implements CommandService {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final StandService standService;
    private final CommandParserService commandParserService;


    @Override
    public SendMessage getMessageFromCommand(String command) {
        String parseCommand = commandParserService.getFirstCommand(command);

        if (MainMenuCommands.MAIN_MENU.name().equals(parseCommand)) {
            return getMainMenuMessage();
        }

        throw new IllegalStateException("Unexpected value: " + command);
    }

    private SendMessage getMainMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(standService.getStandsInfo());
        sendMessage.setReplyMarkup(keyBoardService.getMainMenuKeyBoard());

        return sendMessage;
    }
}
