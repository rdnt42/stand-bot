package com.summer.dev.standbot.service.bot.command;

import com.summer.dev.standbot.constant.keyboard.EquipmentStateCommand;
import com.summer.dev.standbot.service.StandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 15:23
 */
@Slf4j
@AllArgsConstructor
@Service
public class EquipmentStateCommandServiceImpl implements CommandService<EquipmentStateCommand> {

    private final StandService standService;

    @Override
    public SendMessage getMessageFromCommand(EquipmentStateCommand command) {

        return null;
    }

}
