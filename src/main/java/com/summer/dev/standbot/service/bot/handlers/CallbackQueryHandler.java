package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.constant.CallbackDataPartsEnum;
import com.summer.dev.standbot.service.bot.ReplyKeyBoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:23
 */
@AllArgsConstructor
@Service
public class CallbackQueryHandler {

    private final ReplyKeyBoardService replyKeyBoardService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery){
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        if (data.equals(CallbackDataPartsEnum.STATUS.name())) {
            return getCallbackMessage(chatId);
        }

        return null;
    }

    private SendMessage getCallbackMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Тестовый ответ");
        sendMessage.setReplyMarkup(replyKeyBoardService.getMainMenuKeyBoard());
        return sendMessage;
    }
}
