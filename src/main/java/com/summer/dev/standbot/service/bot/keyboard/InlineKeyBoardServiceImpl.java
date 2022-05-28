package com.summer.dev.standbot.service.bot.keyboard;

import com.summer.dev.standbot.constant.keyboard.MainMenuKeyboardCommandEnum;
import com.summer.dev.standbot.constant.keyboard.StandNameCommandEnum;
import com.summer.dev.standbot.repository.StandRepository;
import com.summer.dev.standbot.service.StandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.05.2022
 * Time: 12:57
 */
@AllArgsConstructor
@Service("inlineKeyboardService")
public class InlineKeyBoardServiceImpl implements KeyBoardService<InlineKeyboardMarkup> {
    private final StandService standService;

    @Override
    public InlineKeyboardMarkup getMainMenuKeyBoard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getStatusButton());
        row1.add(getChangeStatusButton());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getStandsNamesKeyBoard() {
        List<List<InlineKeyboardButton>> lists = new ArrayList<>();

        List<String> standsNames = standService.getStandsNames();
        for (String standsName : standsNames) {
            InlineKeyboardButton button = getStandNameButton(standsName);
            List<InlineKeyboardButton> row = Collections.singletonList(button);

            lists.add(row);
        }

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    private InlineKeyboardButton getStatusButton() {
        return InlineKeyboardButton.builder()
                .text(MainMenuKeyboardCommandEnum.SHOW_STATUS.getShowName())
                .callbackData(MainMenuKeyboardCommandEnum.SHOW_STATUS.name())
                .build();
    }

    private InlineKeyboardButton getChangeStatusButton() {
        return InlineKeyboardButton.builder()
                .text(MainMenuKeyboardCommandEnum.CHANGE_STATUS.getShowName())
                .callbackData(MainMenuKeyboardCommandEnum.CHANGE_STATUS.name())
                .build();
    }

    private InlineKeyboardButton getMainMenuButton() {
        return InlineKeyboardButton.builder()
                .text(MainMenuKeyboardCommandEnum.MAIN_MENU.getShowName())
                .callbackData(MainMenuKeyboardCommandEnum.MAIN_MENU.name())
                .build();
    }

    private InlineKeyboardButton getStandNameButton(String standName) {
        return InlineKeyboardButton.builder()
                .text(standName)
                .callbackData(StandNameCommandEnum.STAND_NAME_.name() + standName.toUpperCase())
                .build();
    }
}
