package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.ParseModeTelegramEnum;
import com.summer.dev.standbot.constant.keyboard.StandInfoCommand;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.summer.dev.standbot.constant.keyboard.StandInfoCommand.CHANGE_STATUS;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 23:43
 */
@AllArgsConstructor
@Service
public class StandInfoCommandServiceImpl implements CommandService<StandInfoCommand> {
    private final KeyBoardService<InlineKeyboardMarkup> keyBoardService;

    @Override
    public SendMessage getMessageFromCommand(StandInfoCommand command) {
        if (command.equals(CHANGE_STATUS)) {
            return getChangeStatusMenuMessage();
        }

        throw new IllegalStateException("Unexpected value: " + command);
    }

    private SendMessage getChangeStatusMenuMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseModeTelegramEnum.PARSE_MODE_MARKDOWN.getName());
        sendMessage.setText("*Выберите параметр для изменения*");
        sendMessage.setReplyMarkup(keyBoardService.getEquipmentSelectMenuKeyBoard());

        return sendMessage;
    }

}
