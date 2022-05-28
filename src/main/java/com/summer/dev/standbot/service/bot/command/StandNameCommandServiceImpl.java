package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.StandNameCommandEnum;
import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 14:57
 */
@Slf4j
@AllArgsConstructor
@Service("standNameCommandService")
public class StandNameCommandServiceImpl implements CommandService<StandNameCommandEnum> {
    private final KeyBoardService<? extends ReplyKeyboard> keyBoardService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(StandNameCommandEnum command) {
        log.debug("Get command: " + command.name());

        return getMainMenuMessage();
    }

    private SendMessage getMainMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(standService.getStandsInfo());
        sendMessage.setReplyMarkup(keyBoardService.getMainMenuKeyBoard());

        return sendMessage;
    }
}
