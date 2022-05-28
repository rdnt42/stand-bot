package com.summer.dev.standbot.service.bot;

import com.summer.dev.standbot.config.TelegramConfig;
import com.summer.dev.standbot.service.bot.handlers.CallbackQueryHandler;
import com.summer.dev.standbot.service.bot.handlers.MessageHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:12
 */
@AllArgsConstructor
@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramConfig telegramConfig;

    private final CallbackQueryHandler callbackQueryHandler;
    private final MessageHandler messageHandler;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallBack(update);
        } else {
            handleMessage(update);
        }
    }

    private void handleCallBack(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        callbackQueryHandler.processCallbackQuery(callbackQuery);
    }

    private void handleMessage(Update update) {
        Message message = update.getMessage();
        if (message != null) {
            SendMessage sendMessage = messageHandler.answerMessage(update.getMessage());
            trySendMessage(sendMessage);
        }
    }

    private void trySendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return telegramConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getBotToken();
    }
}
