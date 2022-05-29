package com.summer.dev.standbot.service.bot.keyboard;

import com.summer.dev.standbot.constant.keyboard.EquipmentSelectCommand;
import com.summer.dev.standbot.constant.keyboard.MainMenuKeyboardCommand;
import com.summer.dev.standbot.constant.keyboard.StandInfoCommand;
import com.summer.dev.standbot.constant.keyboard.StandSelectTemplateCommand;
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
        row1.add(getStandSelectButton());
        row1.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = List.of(row1);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getStandSelectMenuKeyBoard() {
        List<List<InlineKeyboardButton>> lists = new ArrayList<>();

        List<String> standsNames = standService.getStandsNames();

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < standsNames.size(); i++) {
            if (i % 2 == 0) {
                row = new ArrayList<>();
                lists.add(row);
            }

            InlineKeyboardButton button = getStandNameButton(standsNames.get(i));
            row.add(button);
        }

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getStandInfoMenuKeyBoard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getStandSelectButton());
        row1.add(getChangeStatusButton());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getEquipmentSelectMenuKeyBoard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getChangeStandStatusButton());
        row1.add(getChangeMetricStatusButton());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getChangeDependentSessionStatusButton());
        row2.add(getChangeIndependentSessionStatusButton());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2, row3);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    private InlineKeyboardButton getStandSelectButton() {
        return InlineKeyboardButton.builder()
                .text(MainMenuKeyboardCommand.STAND_SELECT.getShowName())
                .callbackData(MainMenuKeyboardCommand.STAND_SELECT.name())
                .build();
    }

    private InlineKeyboardButton getChangeStatusButton() {
        return InlineKeyboardButton.builder()
                .text(StandInfoCommand.CHANGE_STATUS.getShowName())
                .callbackData(StandInfoCommand.CHANGE_STATUS.name())
                .build();
    }

    private InlineKeyboardButton getMainMenuButton() {
        return InlineKeyboardButton.builder()
                .text(MainMenuKeyboardCommand.MAIN_MENU.getShowName())
                .callbackData(MainMenuKeyboardCommand.MAIN_MENU.name())
                .build();
    }

    private InlineKeyboardButton getStandNameButton(String standName) {
        return InlineKeyboardButton.builder()
                .text(standName)
                .callbackData(StandSelectTemplateCommand.STAND_SELECT_PREFIX + standName.toUpperCase())
                .build();
    }

    private InlineKeyboardButton getChangeStandStatusButton() {
        return InlineKeyboardButton.builder()
                .text(EquipmentSelectCommand.STATUS_STAND.getShowName())
                .callbackData(EquipmentSelectCommand.STATUS_STAND.name())
                .build();
    }

    private InlineKeyboardButton getChangeMetricStatusButton() {
        return InlineKeyboardButton.builder()
                .text(EquipmentSelectCommand.STATUS_METRIC.getShowName())
                .callbackData(EquipmentSelectCommand.STATUS_METRIC.name())
                .build();
    }

    private InlineKeyboardButton getChangeDependentSessionStatusButton() {
        return InlineKeyboardButton.builder()
                .text(EquipmentSelectCommand.STATUS_DEPENDENT_SESSION.getShowName())
                .callbackData(EquipmentSelectCommand.STATUS_DEPENDENT_SESSION.name())
                .build();
    }

    private InlineKeyboardButton getChangeIndependentSessionStatusButton() {
        return InlineKeyboardButton.builder()
                .text(EquipmentSelectCommand.STATUS_INDEPENDENT_SESSION.getShowName())
                .callbackData(EquipmentSelectCommand.STATUS_INDEPENDENT_SESSION.name())
                .build();
    }
}
