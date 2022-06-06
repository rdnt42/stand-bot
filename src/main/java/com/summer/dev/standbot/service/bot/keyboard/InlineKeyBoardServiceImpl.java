package com.summer.dev.standbot.service.bot.keyboard;

import com.summer.dev.standbot.constant.keyboard.*;
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
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        row1.add(getStandSelectMenuButton());
//        row1.add(getEquipmentSelectButton(standName));
//
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        row2.add(getMainMenuButton());
//
//        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2);
//
//        return InlineKeyboardMarkup.builder()
//                .keyboard(lists)
//                .build();
        return null;
    }


    @Override
    public InlineKeyboardMarkup getEquipmentSelectForStandMenuKeyBoard(String standName) {
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        row1.add(getSelectStatusStandStatusButton(standName));
//        row1.add(getSelectStatusMetricStatusButton(standName));
//
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        row2.add(getSelectStatusDependentSessionStatusButton(standName));
//        row2.add(getSelectStatusIndependentSessionStatusButton(standName));
//
//        List<InlineKeyboardButton> row3 = new ArrayList<>();
//        row3.add(getMainMenuButton());
//
//        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2, row3);
//
//        return InlineKeyboardMarkup.builder()
//                .keyboard(lists)
//                .build();
        return null;
    }

    @Override
    public InlineKeyboardMarkup getStatusSelectForEquipmentKeyBoard(String arguments) {
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        row1.add(getChangeStatusToUnavailableButton(arguments));
//        row1.add(getChangeStatusToUnstableButton(arguments));
//
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        row2.add(getChangeStatusToAvailableButton(arguments));
//
//        List<InlineKeyboardButton> row3 = new ArrayList<>();
//        row3.add(getMainMenuButton());
//
//        List<List<InlineKeyboardButton>> lists = Arrays.asList(row1, row2, row3);
//
//        return InlineKeyboardMarkup.builder()
//                .keyboard(lists)
//                .build();
        return null;
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
        String data = getConcatCommand(StandSelectMenuCommands.STAND_SELECT_MENU.name());

        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(data)
                .build();
    }
//
//    private InlineKeyboardButton getEquipmentSelectButton(String standName) {
//        return InlineKeyboardButton.builder()
//                .text(ChangeStatusCommand.CHANGE_STATUS_NAME)
//                .callbackData(EquipmentSelectCommand.EQUIPMENT_SELECT_PREFIX + standName)
//                .build();
//    }

    private InlineKeyboardButton getMainMenuButton() {
        String text = MainMenuCommands.MAIN_MENU.getDsc();
        String data = getConcatCommand(MainMenuCommands.MAIN_MENU.name());

        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(data)
                .build();
    }

    private InlineKeyboardButton getStandInfoButton(String standName) {
        String text = "Стенд " + standName;
        String data = getConcatCommand(StandSelectCommands.STAND_SELECT.name(), standName.toUpperCase());

        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(data)
                .build();
    }

//    private InlineKeyboardButton getSelectStatusStandStatusButton(String standName) {
//        return InlineKeyboardButton.builder()
//                .text(EquipmentSelectCommand.STAND_NAME)
//                .callbackData(StatusSelectCommand.STATUS_SELECT_PREFIX + EquipmentSelectCommand.STAND + "_" + standName)
//                .build();
//    }
//
//    private InlineKeyboardButton getSelectStatusMetricStatusButton(String standName) {
//        return InlineKeyboardButton.builder()
//                .text(EquipmentSelectCommand.METRIC_NAME)
//                .callbackData(StatusSelectCommand.STATUS_SELECT_PREFIX + EquipmentSelectCommand.METRIC + "_" + standName)
//                .build();
//    }
//
//    private InlineKeyboardButton getSelectStatusDependentSessionStatusButton(String standName) {
//        return InlineKeyboardButton.builder()
//                .text(EquipmentSelectCommand.DEPENDENT_SESSION_NAME)
//                .callbackData(StatusSelectCommand.STATUS_SELECT_PREFIX + EquipmentSelectCommand.DEPENDENT_SESSION + "_" + standName)
//                .build();
//    }
//
//    private InlineKeyboardButton getSelectStatusIndependentSessionStatusButton(String standName) {
//        return InlineKeyboardButton.builder()
//                .text(EquipmentSelectCommand.INDEPENDENT_SESSION_NAME)
//                .callbackData(StatusSelectCommand.STATUS_SELECT_PREFIX + EquipmentSelectCommand.INDEPENDENT_SESSION + "_" + standName)
//                .build();
//    }
//
//    private InlineKeyboardButton getChangeStatusToAvailableButton(String standParams) {
//        return InlineKeyboardButton.builder()
//                .text(ChangeStatusCommand.TO_AVAILABLE_NAME)
//                .callbackData(ChangeStatusCommand.CHANGE_STATUS_PREFIX + ChangeStatusCommand.TO_AVAILABLE + "_" + standParams)
//                .build();
//    }
//
//    private InlineKeyboardButton getChangeStatusToUnavailableButton(String standParams) {
//        return InlineKeyboardButton.builder()
//                .text(ChangeStatusCommand.TO_UNAVAILABLE_NAME)
//                .callbackData(ChangeStatusCommand.CHANGE_STATUS_PREFIX + ChangeStatusCommand.TO_UNAVAILABLE + "_" + standParams)
//                .build();
//    }
//
//    private InlineKeyboardButton getChangeStatusToUnstableButton(String standParams) {
//        return InlineKeyboardButton.builder()
//                .text(ChangeStatusCommand.TO_UNSTABLE_NAME)
//                .callbackData(ChangeStatusCommand.CHANGE_STATUS_PREFIX + ChangeStatusCommand.TO_UNSTABLE + "_" + standParams)
//                .build();
//    }
//

    private String getConcatCommand(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(CommandTags.TAG_END.getTag());
        }

        return sb.toString();
    }
}
