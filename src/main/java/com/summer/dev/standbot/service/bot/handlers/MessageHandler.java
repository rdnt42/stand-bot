package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.ReplyKeyBoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:24
 */
@AllArgsConstructor
@Slf4j
@Service
public class MessageHandler {

    private final StandService standService;

    private final ReplyKeyBoardService replyKeyBoardService;

    public SendMessage answerMessage(Message message) {
        log.info("From id: {} get message: {}", message.getChatId(), message.getText());
        String chatId = message.getChatId().toString();
        sendStartMessage(chatId);

        return sendStartMessage(chatId);
    }

    private SendMessage sendStartMessage(String chatId) {
        List<Stand> stands = standService.getAll();
        String message = getStandsInfo(stands);
        SendMessage sendMessage = new SendMessage(chatId, message);
        log.info("Send message for chat id: {}, message: {}", chatId, message);

        sendMessage.setReplyMarkup(replyKeyBoardService.getMainMenuKeyBoard());

        return sendMessage;
    }

    private String getStandsInfo(List<Stand> stands) {
        StringBuilder sb = new StringBuilder();
        for (Stand stand : stands) {
            sb.append("Стенд: ");
            sb.append(stand.getName());
            sb.append(", статус: ");
            sb.append(stand.getStatus().getName());
            sb.append("\n");
        }

        return sb.toString();
    }
}
