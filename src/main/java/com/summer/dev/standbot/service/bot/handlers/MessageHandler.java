package com.summer.dev.standbot.service.bot.handlers;

import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.keyboard.KeyBoardService;
import com.summer.dev.standbot.service.bot.keyboard.ReplyKeyBoardServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

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

    private final KeyBoardService<? extends ReplyKeyboard> keyBoardService;

    public SendMessage answerMessage(Message message) {
        log.info("From id: {} get message: {}", message.getChatId(), message.getText());
        String chatId = message.getChatId().toString();
        sendStartMessage(chatId);

        return sendStartMessage(chatId);
    }

    private SendMessage sendStartMessage(String chatId) {
        String message = standService.getStandsInfo();
        log.info("Send message for chat id: {}, message: {}", chatId, message);

        return SendMessage.builder()
                .chatId(chatId)
                .text(message)
                .replyMarkup(keyBoardService.getMainMenuKeyBoard())
                .build();
    }
}
