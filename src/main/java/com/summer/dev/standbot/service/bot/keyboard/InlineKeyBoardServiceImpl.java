package com.summer.dev.standbot.service.bot.keyboard;

import com.summer.dev.standbot.constant.keyboard.*;
import com.summer.dev.standbot.service.StandService;
import com.summer.dev.standbot.service.bot.command.encoder.CommandEncoderService;
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
    private final CommandEncoderService commandEncoderService;

    @Override
    public InlineKeyboardMarkup getMainMenuKeyBoard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getStandSelectMenuButton());
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

            InlineKeyboardButton button = getStandInfoButton(standsNames.get(i));
            row.add(button);
        }

        List<InlineKeyboardButton> rowLast = Collections.singletonList(getMainMenuButton());
        lists.add(rowLast);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getStandInfoMenuKeyBoard(String standName) {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getStandSelectMenuButton());
        row1.add(getEquipmentSelectButton(standName));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }


    @Override
    public InlineKeyboardMarkup getEquipmentSelectMenuKeyBoard(String standName) {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getEquipmentStandButton(standName));
        row1.add(getEquipmentMetricButton(standName));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getEquipmentDependentSessionButton(standName));
        row2.add(getEquipmentIndependentSessionButton(standName));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2, row3);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getStatusSelectForEquipmentKeyBoard(String equipmentName, String standName) {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getChangeStatusToUnavailableButton(equipmentName, standName));
        row1.add(getChangeStatusToUnstableButton(equipmentName, standName));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(getChangeStatusToAvailableButton(equipmentName, standName));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2, row3);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getChangeStatusKeyBoard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getMainMenuButton());

        List<List<InlineKeyboardButton>> lists = List.of(row1);

        return InlineKeyboardMarkup.builder()
                .keyboard(lists)
                .build();
    }

    private InlineKeyboardButton getStandSelectMenuButton() {
        String text = StandSelectMenuCommands.STAND_SELECT_MENU.getDsc();
        String data = commandEncoderService.getConcatCommand(StandSelectMenuCommands.STAND_SELECT_MENU.name());

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getEquipmentSelectButton(String standName) {
        String text = EquipmentSelectMenuCommands.EQUIPMENT_SELECT_MENU.getDsc();
        String data = commandEncoderService.getConcatCommand(EquipmentSelectMenuCommands.EQUIPMENT_SELECT_MENU.name(), standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getMainMenuButton() {
        String text = MainMenuCommands.MAIN_MENU.getDsc();
        String data = commandEncoderService.getConcatCommand(MainMenuCommands.MAIN_MENU.name());

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getStandInfoButton(String standName) {
        standName = standName.toUpperCase();
        String text = "Стенд " + standName;
        String data = commandEncoderService.getConcatCommand(StandInfoCommands.STAND_INFO.name(), standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getEquipmentStandButton(String standName) {
        String text = EquipmentSelectCommands.STAND.getDsc();
        String data = commandEncoderService.getConcatCommand(StatusSelectMenuCommands.STATUS_SELECT_MENU.name(),
                EquipmentSelectCommands.STAND.name(),
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getEquipmentMetricButton(String standName) {
        String text = EquipmentSelectCommands.METRIC.getDsc();
        String data = commandEncoderService.getConcatCommand(StatusSelectMenuCommands.STATUS_SELECT_MENU.name(),
                EquipmentSelectCommands.METRIC.name(),
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getEquipmentIndependentSessionButton(String standName) {
        String text = EquipmentSelectCommands.INDEPENDENT_SESSION.getDsc();
        String data = commandEncoderService.getConcatCommand(StatusSelectMenuCommands.STATUS_SELECT_MENU.name(),
                EquipmentSelectCommands.INDEPENDENT_SESSION.name(),
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getEquipmentDependentSessionButton(String standName) {
        String text = EquipmentSelectCommands.DEPENDENT_SESSION.getDsc();
        String data = commandEncoderService.getConcatCommand(StatusSelectMenuCommands.STATUS_SELECT_MENU.name(),
                EquipmentSelectCommands.DEPENDENT_SESSION.name(),
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getChangeStatusToUnavailableButton(String equipmentName, String standName) {
        String text = StatusSelectCommands.STATUS_TO_UNAVAILABLE.getDsc();
        String data = commandEncoderService.getConcatCommand(ChangeStatusCommands.CHANGE_STATUS.name(),
                StatusSelectCommands.STATUS_TO_UNAVAILABLE.name(),
                equipmentName,
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getChangeStatusToAvailableButton(String equipmentName, String standName) {
        String text = StatusSelectCommands.STATUS_TO_AVAILABLE.getDsc();
        String data = commandEncoderService.getConcatCommand(ChangeStatusCommands.CHANGE_STATUS.name(),
                StatusSelectCommands.STATUS_TO_AVAILABLE.name(),
                equipmentName,
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getChangeStatusToUnstableButton(String equipmentName, String standName) {
        String text = StatusSelectCommands.STATUS_TO_UNSTABLE.getDsc();
        String data = commandEncoderService.getConcatCommand(ChangeStatusCommands.CHANGE_STATUS.name(),
                StatusSelectCommands.STATUS_TO_UNSTABLE.name(),
                equipmentName,
                standName);

        return getButtonFromTemplate(text, data);
    }

    private InlineKeyboardButton getButtonFromTemplate(String text, String data) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(data)
                .build();
    }
}
