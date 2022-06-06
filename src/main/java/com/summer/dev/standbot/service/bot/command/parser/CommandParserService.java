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
    // TODO groups doesn't work, but count = 4
    private static final String PREFIX_COMMAND_WITH_TWO_PARAMS = "(\\S*_PREFIX_)(\\S*)_(\\S*)";
    private static final String PREFIX_COMMAND_WITH_THREE_PARAMS = "(\\w+_PREFIX)_(\\w+)_(\\w+)_(\\w+)";
    private static final String WITHOUT_PREFIX_COMMAND = "(\\W*_PREFIX_)(\\W*)";

    public String parseStandName(String command) {
        String regex = CommandTags.TAG_END.getTag();
        String[] strings = command.split(regex);

        if (strings.length == 0) {
            throw new IllegalArgumentException("Cannot parse command: " + command);
        }

        return strings[strings.length - 1];
    }

    public String parseCommand(String command) {
        String regex = CommandTags.TAG_END.getTag();
        String[] strings = command.split(regex);

        if (strings.length == 0) {
            throw new IllegalArgumentException("Cannot parse command: " + command);
        }

        return strings[strings.length - 1];
    }

    public String getArgumentsWithoutPrefixCommand(String command) {
        String[] res = command.split(WITHOUT_PREFIX_COMMAND);

        return res[1];
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
