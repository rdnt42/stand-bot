package com.summer.dev.standbot.service.bot.command.factory;

import com.summer.dev.standbot.constant.keyboard.*;
import com.summer.dev.standbot.service.bot.command.CommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 09.06.2022
 * Time: 23:19
 */
@Slf4j
@AllArgsConstructor
@Service
public class CommandServiceFactory {

    @Qualifier("mainMenuCommandService")
    private final CommandService mainMenuCommandService;

    @Qualifier("standSelectMenuCommandService")
    private final CommandService standSelectMenuCommandService;

    @Qualifier("standInfoCommandService")
    private final CommandService standInfoCommandService;

    @Qualifier("equipmentMenuCommandService")
    private final CommandService equipmentMenuCommandService;

    @Qualifier("statusSelectCommandService")
    private final CommandService statusSelectCommandService;

    @Qualifier("changeStatusCommandService")
    private final CommandService changeStatusCommandService;

    public CommandService getCommandService(String command) {
        log.debug("Command: " + command);

        if (MainMenuCommands.MAIN_MENU.isCommand(command)) {
            return mainMenuCommandService;
        } else if (StandSelectMenuCommands.STAND_SELECT_MENU.isCommand(command)) {
            return standSelectMenuCommandService;
        } else if (StandInfoCommands.STAND_INFO.isCommand(command)) {
            return standInfoCommandService;
        } else if (EquipmentSelectMenuCommands.EQUIPMENT_SELECT_MENU.isCommand(command)) {
            return equipmentMenuCommandService;
        } else if (StatusSelectMenuCommands.STATUS_SELECT_MENU.isCommand(command)) {
            return statusSelectCommandService;
        } else if (ChangeStatusCommands.CHANGE_STATUS.isCommand(command)) {
            return changeStatusCommandService;
        }

        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
