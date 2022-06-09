package com.summer.dev.standbot.service.bot.command.parser;

import com.summer.dev.standbot.constant.keyboard.CommandTags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 17:25
 */
@Slf4j
@Service
public class CommandParserService {

    public String parseStandName(String allCommands) {
        String[] commands = getCommands(allCommands);

        return commands[commands.length - 1];
    }

    public String getFirstCommand(String allCommands) {
        String[] commands = getCommands(allCommands);

        return commands[0];
    }

    public String getNextCommand(String allCommands, String command) {
        String[] commands = getCommands(allCommands);

        for (int i = commands.length - 1; i >= 0; i--) {
            if (commands[i].equals(command) && i + 1 != commands.length) {
                return commands[i + 1];
            }
        }

        return "";

    }

    private String[] getCommands(String allCommands) {
        String regex = CommandTags.TAG_END.getTag();
        String[] strings = allCommands.split(regex);

        if (strings.length == 0) {
            throw new IllegalArgumentException("Cannot parse command: " + allCommands);
        }

        return strings;
    }

    public String parseActionNameByPattern(String command) {
        String[] str = command.split("_");

        return str[3] + "_" + str[4];
    }

    public String parseEquipmentNameByPattern(String command) {
        String[] str = command.split("_");

        return str[5] + "_" + str[6];
    }

    public String parseStandNameByPattern(String command) {
        String[] str = command.split("_");

        return str[7];
    }
}
