package com.summer.dev.standbot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 16:10
 */
@Getter
@Component
public class TelegramConfig {
    @Value("${telegram.bot-name}")
    private String botName;

    @Value("${telegram.bot-token}")
    private String botToken;
}
