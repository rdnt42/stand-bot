package com.summer.dev.standbot.service.bot.command.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String parseStandName(String prefix, String command) {
        String standName = command.replace(prefix, "");

        if (standName.isEmpty()) {
            throw new IllegalArgumentException("Cannot parse stand name from command: " + command);
        }

        return standName;
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
