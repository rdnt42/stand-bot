package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.StandSelectTemplateCommand;
import com.summer.dev.standbot.service.StandService;
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
@Service
public class StandSelectCommandServiceImpl implements CommandService<StandSelectTemplateCommand> {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;
    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(StandSelectTemplateCommand command) {
        log.debug("Get command: " + command.getStandName());
        return getStandInfoMessage(command.getStandName());
    }

    private SendMessage getStandInfoMessage(String standName) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(standService.getStandInfo(standName));
        sendMessage.setReplyMarkup(keyBoardService.getStandInfoMenuKeyBoard());
        sendMessage.setParseMode("Markdown");

        return sendMessage;
    }
}
